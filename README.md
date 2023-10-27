# CSDS-383-No-Names-Microservices

## Versions
Web UI: Node v18.12.0\
CLI: JDK version 19 (Amazon Corretto)\
Databases: PostgresSQL in the cloud via https://aws.amazon.com/rds/

## Explanation of Project
The goal of this project was to develop an application using a layered architecture. The application had the following requirements:

- The client should be able to execute and interact with the application via a web browser and via a CLI.
- The client should be able to create events.
  - Each event should have the following attributes:
    - Event Id: A UUID. If the UUID is not provided, the application should generate one for the
    - event.
    - Event Date
      - The event date must follow the format: YYYY-MM-DD
    - Event Time: Date of the event.
      - The event time must follow the format: HH:MM AM/PM 
    - Event Title: Title of the event.
      - The event title should not be longer than 255 characters.
    - Event Description: Description of the event.
    - The event title should not be longer than 600 characters.
    - Event Host Email: The email of the host of the event.
      - The event host's email should be valid.
      - Invalid emails should be rejected.
- The client should be able to register participants to events.
  - Each event participant should have the following attributes:
    - Event Participant Id:  A UUID. If the UUID is not provided, the application should generate one for the participant.
    - Event Id: Id of the event to which the participant is registered to.
    - Event participant name: Name of the event participant.
      - The name of the event participant should not be longer than 600 characters.
    - Event participant email: Email of the event participant.
      - The event participant's email should be valid string (format). No need to authenticate.
      - Invalid emails should be rejected.
- All events and event participants should be stored in single or multiple databases (any relational or object-based database works for this exercise). The team should decide the best approach to fit the requirements using microservices.
- The client should be able to list all the events and event participants available stored in the system (database).
- The application should be developed using microservices. The team should decide the best approach to design those microservices, and the expected number of microservices to be developed.
- The CLI  should be able to be executed in a Unix-based or Windows environment.

## How to run the project
- TODO
