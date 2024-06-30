//API CONTEXT
getProducts = () => {
    return fetch("/api/products")
        .then(response => response.json());
}

getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json());
}

const addProductToCart = (productId) => {
    return fetch(`/api/add-to-cart/${productId}`, {
        method: 'POST'
    });
}

const acceptOffer = (acceptOfferRequest) => {
    return fetch("/api/accept-offer", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    })
        .then(response => response.json());
}

createProductHtmlEl = (productData) => {
    const template = `
        <div>
            <img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.templeofbricks.com%2Fimg%2Fsets%2Ffull%2Flego-star-wars%2Fset-lego-star-wars-75058-1.jpg&f=1&nofb=1&ipt=8e03317fc3da24610946b77d2f3f5db51daac0dd98fd71e4f0ed932fc11415f3&ipo=images" width= 200 height = 200 />
            <h4>${productData.name}</h4>
            <span>${productData.description}</span>
            <span>${productData.price}</span>
            <button data-id ="${productData.id}"> Add to cart</button>
        </div>
    `;
    const newEl = document.createElement("li");
    newEl.innerHTML = template.trim();
    return newEl;
}

const refreshCurrentOffer = () => {
    const totalEl = document.querySelector("#offer__total")
    const itemCountEl = document.querySelector("#offer__items-count")

    getCurrentOffer()
        .then(offer => {
            totalEl.textContent = `${offer.total} PLN`;
            itemCountEl.textContent = `${offer.itemCount}`;
        })
}

const initializeCartHandler = (productHtmlEl) => {
    const addToCartBtn = productHtmlEl.querySelector("button[data-id]");
    addToCartBtn.addEventListener("click", (event) => {
        const productId = event.target.getAttribute("data-id")
        addProductToCart(productId)
            .then(refreshCurrentOffer());
        console.log("adding product to cart")
    });
    return productHtmlEl;
}

const checkoutFormEl = document.querySelector(('#checkout'))
checkoutFormEl.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstName: checkoutFormEl.querySelector('input[name="first_name"]').value,
        lastName: checkoutFormEl.querySelector('input[name="lastname_name"]').value,
        email: checkoutFormEl.querySelector('input[name="email"]').value
    }

    acceptOffer(acceptOfferRequest)
        .then(resDetails => {window.location.href = resDetails.paymentUrl})
})

document.addEventListener("DOMContentLoaded", () => {
    console.log("it works");
    const productsList = document.querySelector("#productsList");
    getProducts()
        .then(products => products.map(createProductHtmlEl))
        .then(productsHtmls => productsHtmls.map(initializeCartHandler))
        .then(productsHtmls => {
            productsHtmls.forEach(htmlEl => productsList.appendChild(htmlEl))
        });
})