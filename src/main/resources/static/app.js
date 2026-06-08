const API_BASE = '/api';
const AUTH_STORAGE_KEY = 'contallAuth';
let auth = null;
let editingProductId = null;

function saveAuth(username, password) {
    localStorage.setItem(AUTH_STORAGE_KEY, JSON.stringify({ username, password }));
    auth = { username, password };
}

function clearAuth() {
    localStorage.removeItem(AUTH_STORAGE_KEY);
    auth = null;
    editingProductId = null;
}

function loadAuth() {
    const stored = localStorage.getItem(AUTH_STORAGE_KEY);
    if (!stored) return null;
    try {
        return JSON.parse(stored);
    } catch {
        return null;
    }
}

function getAuthHeader() {
    if (!auth) return null;
    return 'Basic ' + btoa(`${auth.username}:${auth.password}`);
}

function showMessage(text, type = 'info') {
    const msg = document.getElementById('message');
    msg.textContent = text;
    msg.className = type;
    msg.style.display = 'block';
}

function hideMessage() {
    const msg = document.getElementById('message');
    msg.style.display = 'none';
}

function setAuthenticated(authenticated) {
    document.getElementById('login-section').style.display = authenticated ? 'none' : 'block';
    document.getElementById('inventory-section').style.display = authenticated ? 'block' : 'none';
    document.getElementById('logout-button').style.display = authenticated ? 'inline-block' : 'none';
}

async function login(event) {
    event.preventDefault();
    hideMessage();
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    if (!username || !password) {
        showMessage('Ingresa usuario y clave.', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();
        if (!response.ok) {
            showMessage(data.message || 'Credenciales inválidas.', 'error');
            return;
        }

        saveAuth(username, password);
        setAuthenticated(true);
        document.getElementById('login-form').reset();
        await loadProducts();
        showMessage('Inicio de sesión exitoso.', 'success');
    } catch (err) {
        showMessage('Error de conexión. Asegúrate de que el backend esté en ejecución.', 'error');
    }
}

async function fetchWithAuth(path, options = {}) {
    const headers = options.headers || {};
    const authHeader = getAuthHeader();
    if (!authHeader) throw new Error('Sin credenciales');
    headers['Authorization'] = authHeader;
    headers['Accept'] = 'application/json';
    if (options.body && !(options.body instanceof FormData)) {
        headers['Content-Type'] = 'application/json';
    }
    const response = await fetch(path, { ...options, headers });
    if (response.status === 401) {
        clearAuth();
        setAuthenticated(false);
        showMessage('Sesión expirada o credenciales inválidas. Por favor inicia sesión de nuevo.', 'error');
        throw new Error('No autorizado');
    }
    return response;
}

async function loadProducts() {
    try {
        const response = await fetchWithAuth(`${API_BASE}/products`);
        const products = await response.json();
        renderProducts(products);
    } catch (err) {
        console.error(err);
    }
}

function renderProducts(products) {
    const tbody = document.getElementById('products-body');
    tbody.innerHTML = '';
    if (products.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7">No hay productos registrados.</td></tr>';
        return;
    }

    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.id || ''}</td>
            <td>${product.sku || ''}</td>
            <td>${product.name || ''}</td>
            <td>${product.description || ''}</td>
            <td>${product.quantity ?? ''}</td>
            <td>$${product.price?.toFixed(2) ?? ''}</td>
            <td class="actions">
                <button type="button" class="btn edit" data-id="${product.id}">Editar</button>
                <button type="button" class="btn delete" data-id="${product.id}">Eliminar</button>
            </td>
        `;
        tbody.appendChild(row);
    });

    tbody.querySelectorAll('.edit').forEach(button => {
        button.addEventListener('click', () => startEdit(button.dataset.id));
    });
    tbody.querySelectorAll('.delete').forEach(button => {
        button.addEventListener('click', () => deleteProduct(button.dataset.id));
    });
}

async function startEdit(id) {
    hideMessage();
    try {
        const response = await fetchWithAuth(`${API_BASE}/products/${id}`);
        const product = await response.json();
        editingProductId = product.id;
        document.getElementById('form-title').textContent = 'Editar producto';
        document.getElementById('sku').value = product.sku || '';
        document.getElementById('name').value = product.name || '';
        document.getElementById('description').value = product.description || '';
        document.getElementById('quantity').value = product.quantity ?? '';
        document.getElementById('price').value = product.price ?? '';
        document.getElementById('submit-button').textContent = 'Actualizar';
    } catch (err) {
        console.error(err);
    }
}

async function deleteProduct(id) {
    hideMessage();
    if (!confirm('¿Eliminar este producto?')) return;
    try {
        const response = await fetchWithAuth(`${API_BASE}/products/${id}`, { method: 'DELETE' });
        if (!response.ok) {
            showMessage('No se pudo eliminar el producto.', 'error');
            return;
        }
        await loadProducts();
        showMessage('Producto eliminado.', 'success');
    } catch (err) {
        console.error(err);
    }
}

async function saveProduct(event) {
    event.preventDefault();
    hideMessage();
    const sku = document.getElementById('sku').value.trim();
    const name = document.getElementById('name').value.trim();
    const description = document.getElementById('description').value.trim();
    const quantity = Number(document.getElementById('quantity').value);
    const price = Number(document.getElementById('price').value);

    if (!sku || !name || isNaN(quantity) || isNaN(price)) {
        showMessage('Completa los campos requeridos correctamente.', 'error');
        return;
    }

    const payload = { sku, name, description, quantity, price };
    const method = editingProductId ? 'PUT' : 'POST';
    const url = editingProductId ? `${API_BASE}/products/${editingProductId}` : `${API_BASE}/products`;

    try {
        const response = await fetchWithAuth(url, {
            method,
            body: JSON.stringify(payload)
        });
        if (!response.ok) {
            const error = await response.text();
            showMessage('Error guardando producto: ' + error, 'error');
            return;
        }
        await loadProducts();
        resetForm();
        showMessage(editingProductId ? 'Producto actualizado.' : 'Producto creado.', 'success');
    } catch (err) {
        console.error(err);
    }
}

function resetForm() {
    editingProductId = null;
    document.getElementById('form-title').textContent = 'Agregar nuevo producto';
    document.getElementById('submit-button').textContent = 'Guardar producto';
    document.getElementById('product-form').reset();
}

function logout() {
    clearAuth();
    setAuthenticated(false);
    resetForm();
    showMessage('Sesión cerrada.', 'info');
}

window.addEventListener('DOMContentLoaded', async () => {
    auth = loadAuth();
    setAuthenticated(!!auth);
    document.getElementById('login-form').addEventListener('submit', login);
    document.getElementById('product-form').addEventListener('submit', saveProduct);
    document.getElementById('cancel-edit').addEventListener('click', event => {
        event.preventDefault();
        resetForm();
        hideMessage();
    });
    document.getElementById('logout-button').addEventListener('click', logout);

    if (auth) {
        await loadProducts();
    }
});
