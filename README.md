# Customer Feedback System

This project is a full-stack application for collecting and managing customer feedback. It consists of a React frontend and a Spring Boot backend.

## Project Structure

The project is divided into two main parts:

1. Frontend (React)
2. Backend (Spring Boot)

### Frontend

Located in the `frontend` directory.

#### Key Files and Directories:

- `src/components/FeedbackForm.tsx`: Main component for the feedback form
- `src/services/feedbackService.ts`: Service for making API calls to the backend
- `src/App.tsx`: Main application component
- `public/index.html`: HTML template

#### Setup and Running:

1. Navigate to the `frontend` directory
2. Install dependencies: `npm install`
3. Start the development server: `npm start`

The frontend will be available at `http://localhost:3000`.

### Backend

Located in the root directory.

#### Key Files and Directories:

- `src/main/java/com/company/customerfeedback/`: Root package
  - `domain/`: Domain models
  - `application/`: Application services
  - `infrastructure/`: Infrastructure layer (web, persistence)
  - `ports/`: Ports for hexagonal architecture
- `src/test/`: Test classes

#### Setup and Running:

1. Ensure you have Java 21 and Maven installed
2. Configure your database settings in `src/main/resources/application.properties`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

The backend API will be available at `http://localhost:8080`.

## API Endpoints

- POST `/api/feedback`: Submit feedback
  - Request body: `{ "name": "string", "email": "string", "feedbackText": "string" }`

## Database

The application uses PostgreSQL. Make sure to create a database named `customer_feedback` before running the application.

## Testing

- Frontend: Run `npm test` in the `frontend` directory
- Backend: Run `mvn test` in the root directory

## Development Workflow

1. Make changes to the frontend or backend code
2. Run tests to ensure everything is working correctly
3. Start both frontend and backend servers
4. Test the application manually through the UI

## Deployment

- Frontend: Build the React app using `npm run build` and serve the static files
- Backend: Package the application using `mvn package` and deploy the resulting JAR file

## Contributing

1. Fork the repository
2. Create a new branch for your feature
3. Make your changes and write tests
4. Submit a pull request

Please ensure that your code follows the existing style and passes all tests before submitting a pull request.

## Troubleshooting

- If you encounter CORS issues, ensure that the backend's CORS configuration in `WebConfig.java` matches your frontend's URL
- For database connection issues, double-check your database configuration in `application.properties`
