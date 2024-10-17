# Dynamic PDF Generation

This project is a Spring Boot application that generates PDF documents dynamically using an HTML template and the iText library. The application exposes REST APIs for generating, storing, and redownloading PDFs based on invoice data.

## Features

- **Dynamic PDF Generation**: Generates PDFs from an HTML template with Thymeleaf and iText.
- **RESTful APIs**: Supports endpoints for generating and redownloading PDFs.
- **Storage Mechanism**: Stores generated PDFs locally to avoid regenerating for the same data.
- **Thymeleaf Integration**: Uses Thymeleaf templates for flexible HTML-based PDF generation.

## Technologies Used

- **Java 17**: Core programming language.
- **Spring Boot**: Application framework.
- **Thymeleaf**: Template engine for generating HTML content.
- **iText (html2pdf)**: Library for converting HTML content to PDF.
- **Maven**: Dependency management and build tool.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java 17**
- **Maven**

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/dynamic-pdf-generation.git
    ```

2. Navigate to the project directory:

    ```bash
    cd dynamic-pdf-generation
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### 1. Generate PDF

- **URL**: `/api/pdf/generate`
- **Method**: `POST`
- **Request Body**:
  
    ```json
    {
      "seller": "XYZ Pvt. Ltd.",
      "sellerGstin": "29AABBCCDD121ZD",
      "sellerAddress": "New Delhi, India",
      "buyer": "Vedant Computers",
      "buyerGstin": "29AABBCCDD131ZD",
      "buyerAddress": "New Delhi, India",
      "items": [
        {
          "name": "Product 1",
          "quantity": "12 Nos",
          "rate": 123.00,
          "amount": 1476.00
        }
      ]
    }
    ```

- **Response**: A downloadable PDF file.

### 2. Redownload PDF

- **URL**: `/api/pdf/redownload`
- **Method**: `POST`
- **Request Body**: Same as the "Generate PDF" request body.
- **Response**: A downloadable PDF file if it already exists.

## Project Structure


## Usage

1. Send a `POST` request to `/api/pdf/generate` with the invoice data in JSON format to generate a PDF.
2. The generated PDF can be downloaded directly or stored in the `pdf_storage` folder.
3. You can redownload a previously generated PDF by sending the same request to `/api/pdf/redownload`.

### Explanation of Sections:
1. **Project Overview**: Gives a clear introduction and explains what the project does.
2. **Technologies Used**: Lists the core technologies and libraries that are used.
3. **Getting Started**: Provides instructions on how to clone, build, and run the project.
4. **API Endpoints**: Documents the key API endpoints (such as generating and downloading PDFs).
5. **Project Structure**: Explains the project’s folder structure for easy navigation.
6. **Usage**: Provides a simple explanation of how to interact with the API endpoints.
7. **Contributing and License**: Information for contributing to the project and the license under which it is shared.

### Next Steps
- Replace the placeholder GitHub link with your actual repository link.
- Update any values (like API examples, version numbers, etc.) based on your project.
- Include a `LICENSE` file if applicable.

Let me know if you need further assistance in adjusting the `README.md` file!
## Example Invoice Template

The PDF is generated using a Thymeleaf HTML template, which is converted to PDF via iText:

```html
<div class="info-section">
    <div>
        <strong>Seller:</strong> <span th:text="${invoice.seller}"></span>
        <br/>
        GSTIN: <span th:text="${invoice.sellerGstin}"></span>
    </div>
    <div>
        <strong>Buyer:</strong> <span th:text="${invoice.buyer}"></span>
        <br/>
        GSTIN: <span th:text="${invoice.buyerGstin}"></span>
    </div>
</div>
