# Customer Feedback Analytics & Rotational Menu Planner

A portfolio-ready Java/Spring Boot service that combines customer-feedback sentiment/topic
analytics with an OR-Tools CP-SAT rotational menu planning API.

## Stack
Java 17, Spring Boot, PostgreSQL, DJL/DistilBERT, Google OR-Tools CP-SAT, Power BI,
Docker, GitHub Actions, JUnit.

## Run
```bash
# Set DB_PASSWORD in your environment before starting the stack.
docker compose up --build
```

API:
- `GET /api/health`
- `POST /api/feedback/analyze`
- `POST /api/menu/plan`

The implementation includes a deterministic lightweight sentiment/topic fallback so the
service remains runnable even when a transformer model is not downloaded. Production
deployment can replace the fallback with DJL DistilBERT inference.
