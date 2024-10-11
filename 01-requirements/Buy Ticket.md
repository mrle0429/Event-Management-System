# Buy Ticket

## Description

This use case allows users to purchase tickets for an event. Users can select the type and number of tickets they want, proceed with payment, and confirm their purchase. Payment processing is handled separately and is not included in this use case.

## Actors

Users

## Triggers

This use case is triggered when a user decides to purchase tickets for a specific event.

## Preconditions

- The user must be logged in.
- Tickets for the event must be available.
- The event must not be canceled or sold out.

## Postconditions

- The selected tickets are reserved for the user.
- The user’s ticket purchase is recorded in the system.

## Courses of Events

### 01 - Basic course of events
#### Course of Events
1. The user navigates to the event details page.
2. The user clicks the "Buy Ticket" button.
3. The system displays a list of available ticket types (e.g., standing, seating, VIP) with their prices and availability.
4. The user selects the ticket type and the quantity they wish to purchase.
5. The user clicks the "Proceed to Payment" button.
6. The system redirects the user to a payment gateway for completing the transaction.
7. The user completes the payment.
8. Upon successful payment, the system confirms the purchase and reserves the tickets for the user.
9. The system sends a confirmation email with ticket details to the user.

#### Related UI Prototypes
| 05 - Buy Ticket Page |
|:--:|
| ![Buy Ticket](ui/BuyTicket.png) |

### 02 - Alternate course of events - Insufficient Tickets Available
#### Course of Events
1. The user navigates to the event details page and clicks "Buy Ticket."
2. The system displays the available ticket types.
3. The user selects a quantity greater than the available tickets.
4. The system informs the user that there are insufficient tickets available and allows them to adjust their selection.
5. The user selects a valid quantity of tickets and proceeds with the purchase.

#### Related UI Prototypes
| 05 - Buy Ticket Error Page |
|:--:|
| ![Buy Ticket Error](ui/BuyTicketError.png) |

### 03 - Alternate course of events - Payment Failure
#### Course of Events
1. The user completes steps 1 through 5 as in the basic course.
2. The system redirects the user to the payment gateway.
3. The user’s payment fails due to insufficient funds or other issues.
4. The system informs the user that the payment failed and allows them to retry or cancel the purchase.
5. The user retries payment or cancels the transaction.

#### Related UI Prototypes
| 05 - Payment Failure Page |
|:--:|
| ![Payment Failure](ui/PaymentFailure.png) |

## API Endpoints

- `POST /tickets/purchase`: Reserves tickets for the user and initiates the payment process.
- `GET /events/{eventId}/tickets`: Fetches available ticket types and quantities for a specific event.
- `POST /payments`: Redirects the user to the payment gateway.

## Inclusions

- Validate Ticket Availability
- Process Payment
- Send Confirmation Email

## Data Outcomes

**READ** - The system retrieves ticket information for the selected event.
**CREATE** - A ticket purchase record is created for the user.

---

## Features

- **Ticket Selection**:
    - The user can view available ticket types (e.g., standing, seating, VIP).
    - The user can select the type and number of tickets to purchase.

- **Payment Processing**: 
    - After selecting tickets, the user is redirected to a secure payment gateway.
    - The system confirms the purchase after successful payment.

- **Confirmation**:
    - A confirmation message is displayed after the purchase is completed.
    - The user receives an email with ticket details, including event information and a unique ticket ID.

## Future Enhancements

- **Discount Codes**: Add support for discount codes or promotions during ticket purchase.
- **Gift Tickets**: Allow users to purchase tickets as gifts for others.
- **Refund and Exchange**: Enable users to request refunds or exchange tickets for another event.
