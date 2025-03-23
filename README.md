# OCR Label Matching Backend – Application Flow

This backend application is built with **Java + Spring Boot** using **Hexagonal Architecture**. It processes uploaded label images, sends them to an external OCR service, extracts text, intelligently matches the result to product data, and optionally links a **Handling Unit (HU)** to the matched product.

---

## Overview of the Application Flow

The system operates in the following stages:

1. User uploads a label image via frontend
2. Backend forwards the image to an OCR microservice
3. Extracted text is received from OCR
4. Backend normalizes the text and performs matching against product records
5. Best-matching product is selected
6. A detailed match result is returned
7. Optionally, the matched product is linked to a Handling Unit (HU)

---

## Step 1: Uploading the Image

**Endpoint:**  
`POST /api/ocr/extract-text`

**Request Parameters:**
- `file`: The image of the product label
- `orderNumber` (optional): EDI order number to limit product search scope
- `hu` (optional): Handling Unit number to be linked post-match

**Action:**
- The backend sends the uploaded file to the external OCR service
---

## Step 2: OCR Text Extraction

- OCR service returns raw text as a list of strings
---

## Step 3: Text Normalization & Matching

The backend now processes and matches the extracted text using the `ProductMatchingUseCaseImpl`:

### Phase 1: Exact Match
- Checks if any word exactly matches a known `productCode`
- If found, compares additional fields:
    - `batch`
    - `customerName`
    - `description`

### Phase 2: Fuzzy Match (Fallback)
- If no exact match, uses Levenshtein distance to compute similarity
- **Weighted accuracy** is calculated:
    - Product Code: 40%
    - Batch: 30%
    - Customer Name: 20%
    - Description: 10%

The product with the **highest overall match score** is selected.

---

## Step 4: Match Result Returned

The backend responds with a structured DTO containing:

- Matched product details
- Whether the match was exact or fuzzy
- List of extracted words/phrases
- Overall accuracy score
- Detailed match info for each field

---

## Step 5: Linking HU to Product (Optional)

Once a match is returned, the frontend may trigger a link operation.

**Endpoint:**  
`POST /api/match`

**Request Parameters:**
- `hu`: The Handling Unit number
- `product_id`: The matched product ID
- `batch`: The batch of the matched product

**Action:**
- The backend verifies and links the HU to the selected product in the database

---

## Supporting Endpoints

| Method | URL                             | Description                          |
|--------|----------------------------------|--------------------------------------|
| GET    | `/api/on?orderNumber=...`        | Validates if the order number exists |
| GET    | `/api/hu/search?query=...`       | Search or fetch available HUs        |
| POST   | `/api/ocr/extract-text`          | Upload image & return match result   |
| POST   | `/api/match`                     | Link HU to a matched product         |

---

##  Architecture – Hexagonal Pattern

The application follows Hexagonal Architecture

