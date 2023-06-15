## Payment Gateway Integration for Stripe, PayPal and CoinPayments

This project is a comprehensive payment gateway integration system that combines three popular payment processors: Stripe, PayPal, and CoinPayments. The system is built using the Spring Boot framework for the backend and React.js with Redux for the frontend.

### Key Features:

1. Stripe Integration: Seamlessly integrate Stripe to allow users to make payments using credit cards, debit cards, and other supported payment methods. The system ensures the highest level of data security through secure tokenization of sensitive payment information.
2. PayPal Integration: Provide users with the option to make payments using their PayPal accounts. The system securely handles the entire PayPal payment process, including authentication and transaction management.
3. CoinPayments Integration: Support cryptocurrency payments through CoinPayments. Users can select from a variety of cryptocurrencies and complete transactions securely.
4. User-Friendly Interface: The frontend, developed using React.js and Redux, offers a responsive and intuitive user interface. Real-time updates and feedback on the payment status provide a seamless payment experience.
5. Transaction Management: Efficiently manage transactions, including order processing, payment verification, and error handling. The system ensures reliability and integrity throughout the payment process.
6. Customization and Scalability: The architecture of the project is designed to be easily scalable and customizable. It allows for the future addition of new payment processors, providing flexibility for further expansion.


### API Reference

#### Products Controller Requests

##### Create product 
```
  POST /product
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name`    | `string` | **Required**. Product name |
| `price`   | `float`  | **Required**. Product price |
| `brand`   | `string`  | **Required**. Product brand |
| `category`   | `string`  | **Required**. Category of product |

##### Get all products

```
  GET /products
```

#### Invoice Controller Request

##### Get all invoices
```
  GET /invoices
```

#### CoinPayments Controller Requests

##### Create payment
```
  POST /crypto/create/payment
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `productId`    | `string` | **Required**. Product id |
| `email`   | `string`  | **Required**. Buyer email |
| `cryptocurrency` | `string`  | **Required**. Code of cryptocurrency |

#### PayPal and Stripe Controller Requests 

##### Create payment
```
  POST /{method}/create/payment
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `method`    | `string` | **Required**. `paypal` or `stripe` |
| `productId`   | `string`  | **Required**. Product id |



### Tech Stack

**Client:** React, Redux, TailwindCSS, SCSS

**Server:** Spring Boot, PostgreSQL, RestClient, Hibernate-JPA

### Frontend UI photos
![front_end_example](./docs/payment.png)
![front_end_example](./docs/invoices.png)
![front_end_example](./docs/products.png)

`Developed by byko-dev 2023`
