let token = "";

function register() {
  const data = {
    name: document.getElementById("regName").value,
    email: document.getElementById("regEmail").value,
    password: document.getElementById("regPassword").value
  };
  fetch("http://localhost:8080/api/auth/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  }).then(res => res.text()).then(alert);
}

function login() {
  const data = {
    email: document.getElementById("loginEmail").value,
    password: document.getElementById("loginPassword").value
  };
  fetch("http://localhost:8080/api/auth/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
  .then(res => res.json())
  .then(data => {
    token = data.token;
    alert("Login successful!");
  });
}

function getProducts() {
  fetch("http://localhost:8080/api/products")
    .then(res => res.json())
    .then(data => {
      const html = data.content.map(p => `<p>${p.name} - ₹${p.price}</p>`).join("");
      document.getElementById("products").innerHTML = html;
    });
}

function addToCart() {
  const data = {
    productId: parseInt(document.getElementById("cartProductId").value),
    quantity: parseInt(document.getElementById("cartQuantity").value)
  };
  fetch("http://localhost:8080/api/cart", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    },
    body: JSON.stringify(data)
  }).then(res => res.json()).then(() => alert("Added to cart!"));
}

function viewCart() {
  fetch("http://localhost:8080/api/cart", {
    headers: { "Authorization": "Bearer " + token }
  })
  .then(res => res.json())
  .then(data => {
    const html = data.map(item => `<p>${item.product.name} x ${item.quantity}</p>`).join("");
    document.getElementById("cartItems").innerHTML = html;
  });
}

function placeOrder() {
  fetch("http://localhost:8080/api/orders", {
    method: "POST",
    headers: { "Authorization": "Bearer " + token }
  }).then(() => alert("Order placed!"));
}

function viewOrders() {
  fetch("http://localhost:8080/api/orders", {
    headers: { "Authorization": "Bearer " + token }
  })
  .then(res => res.json())
  .then(data => {
    const html = data.map(order => `
      <div>
        <h4>Order ID: ${order.id}</h4>
        <p>Date: ${order.orderDate}</p>
        ${order.items.map(i => `<p>${i.product.name} x ${i.quantity}</p>`).join("")}
      </div>
    `).join("");
    document.getElementById("orders").innerHTML = html;
  });
}
