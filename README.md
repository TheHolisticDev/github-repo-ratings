# Github repo settings

Simple demo project to create an API to fetch and score repos from the Github API.

# What's missing

## Documentation

### Architecture

The systems involved should be documented and visualized.

### API

* The API should be documented in a standard way (e.g. OpenAPI). 
* Ideally the project would contain for example an Insomnia project (or a similar collaborative API description and testing tool) to make it easily accessible.

### Dev docs

* Documentation about the project setup.

### Code

Being a tiny microservice, the code itself is simple and documents itself mostly. There might be some special areas that could do with some explanations (e.g. ClockProvider to mock Clock in tests).

### Business side

We are missing the business side completely. What's the purpose of the service etc.

## Tests

Following the principles of [https://testing-library.com/] emphasize was put on creating confidence with integration tests.
More edge cases and higher test coverage are still needed though (e.g. unit tests for scoring mechanism).

## Caching

As of time of stopping development I supposed the caching eviction was not working properly. This would need further investigation.

## Validation and specific error handling

The API could do with some more consistend input validation and more graceful error communication. This would depend on the consumers and contracts that need to be satisfied.

## Is this modern Java?

More time could go into researching the most elegant and yet stable way of doing things in modern JAVA environments. Emphasize should be put on ease of use, stability and simplicity. Maybe we even would want to investigate Kotlin instead of Java ;).

## Polishing

To deploy this as a production micro service a lot of context questions would need to be asked and answered. Topics like API discovery, security, logging mechanisms and deployment environment need to be covered.
