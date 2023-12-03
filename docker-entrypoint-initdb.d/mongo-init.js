db.createUser(
    {
        user: "admin",
        pwd: "qwerty12345",
        roles: [
            {
                role: "readWrite",
                db: "op"
            }
        ]
    }
)

db.createCollection('order')
db.createCollection('product')

db.product.createIndex( { sku: 1}, { unique: true} )

db.product.insertMany([
    { sku: "00001", name: "Product_1", description: "Product_1 Description", qty: 1000.00, price: 10.00},
    { sku: "00002", name: "Product_2", description: "Product_2 Description", qty: 1000.00, price: 15.00},
    { sku: "00003", name: "Product_3", description: "Product_3 Description", qty: 1000.00, price: 20.00},
    { sku: "00004", name: "Product_4", description: "Product_4 Description", qty: 1000.00, price: 0.50},
    { sku: "00005", name: "Product_5", description: "Product_5 Description", qty: 1000.00, price: 35.50},
    { sku: "00006", name: "Product_6", description: "Product_6 Description", qty: 1000.00, price: 40.00},
    { sku: "00007", name: "Product_7", description: "Product_7 Description", qty: 1000.00, price: 37.75},
    { sku: "00008", name: "Product_8", description: "Product_8 Description", qty: 1000.00, price: 32.46},
    { sku: "00009", name: "Product_9", description: "Product_9 Description", qty: 1000.00, price: 12.00},
    { sku: "00010", name: "Product_10", description: "Product_10 Description", qty: 1000.00, price: 54.45},
    { sku: "00011", name: "Product_11", description: "Product_11 Description", qty: 1000.00, price: 10.78},
    { sku: "00012", name: "Product_12", description: "Product_12 Description", qty: 1000.00, price: 23.45},
    { sku: "00013", name: "Product_13", description: "Product_13 Description", qty: 1000.00, price: 34.65},
    { sku: "00014", name: "Product_14", description: "Product_14 Description", qty: 1000.00, price: 456.05},
    { sku: "00015", name: "Product_15", description: "Product_15 Description", qty: 1000.00, price: 78.45},
    { sku: "00016", name: "Product_16", description: "Product_16 Description", qty: 1000.00, price: 23.34},
    { sku: "00017", name: "Product_17", description: "Product_17 Description", qty: 1000.00, price: 34.99},
    { sku: "00018", name: "Product_18", description: "Product_18 Description", qty: 1000.00, price: 54.39},
    { sku: "00019", name: "Product_19", description: "Product_19 Description", qty: 1000.00, price: 56.23},
    { sku: "00020", name: "Product_20", description: "Product_20 Description", qty: 1000.00, price: 45.89},
    { sku: "00021", name: "Product_21", description: "Product_21 Description", qty: 1000.00, price: 34.59},
])
