# TWITTER

## Description

Social media application.

## Getting Started

### Dependencies

* Requires Java 17, Spring boot 3, Docker, AWS S3, Postgres, RabbitMQ, Redis, Websocket, Gradle ... basic understanding. Hosted this project along with Redis, MinIO, Postgres instances on Render. Hosted RabbitMQ instance via CLoudAMPQ.

## Contributing

Contributions are always welcome! We are thrilled that you're interested in contributing to our project! Contributing to this project is a chance to make a positive impact on our software, interact with the community, and grow your skills. Here’s how you can contribute.

### Prerequisites

Before you begin, ensure you have the following installed:
- Git
- A local development environment compatible with our technology stack

### Setting Up Your Development Environment

1. **Clone the repository**
   - On your GitHub, go to our repository.
   - Click on the "Code" button and copy the URL for the repository.
   - Open a terminal on your computer and run the following git command:
     ```bash
     git clone https://github.com/QuocBaoBuiNguyen/twitter
     ```
3. **Create a New Branch**
   - Navigate into the cloned directory:
     ```bash
     cd twitter
     ```
   - Based on your feature, you might want to checkout a new branch from specific microservice:
     ```bash
     git checkout feature/ms-service-name
     git checkout -b feature/ms-service-name/feature-name
     ```

   - You might want to checkout a new branch from develop branch. In this case, please make sure that update the service-name on the Dockerfile based on which service might be impacted from the changes you have made before raising new PR to develop branch. We will need this Dockerfile to be updated in develop branch before raising new PR from dev to deploy branch to have your changes in new future release.

### Making Contributions

1. **Follow the Project Guidelines**
   - Read and follow the guidelines in the `CONTRIBUTING.md` file located in the root directory of the repository (if available). For now we don't have the particular coding convention but will notice it.

2. **Make Changes Locally**
   - Make your changes locally and commit them to your branch. Make sure your commits are small and focused. Also, provide an explanatory commit message:
     ```bash
     git commit -m "Add a concise commit message describing your change"
     ```

3. **Run Tests**
   - Currently we don't have any unit/component/blackbox tests for our services. Feel free to contribute that for our repository to help our services 
   - Ensure that your changes pass all the tests. It's important to maintain the health of the application.

4. **Push Changes to GitHub**
   - Push your changes:
     ```bash
     git push origin your-new-branch-name
     ```

5. **Make sure your changes pass the CI pipeline before raising PR**
   - Follow and update your changes if it failed CI pipeline due to any issue (vulnerabilities, tests failure, coding style...);
     
### Submitting Changes

1. **Create a Pull Request (PR)**
   - *Please notice that if you have checked out from any feature/ms-service-name branch, you would need to raise your PR to that feature/ms-service-name branch again before we continue proceeding from that service branch to develop branch once your PR has been approved. Otherwise if you checked out from develop branch, please ensure that the Dockerfile has been updated to help your changes will be IN EFFECT when we deploy.*
   - *Also please note that for now, do not raise any PR from develop branch to any feature/ms-service-name branch as we're still remaining different Dockerfile versions between different branches to help our deployment. If you do that, the Dockerfile from service branch would be impacted and hard to maintain in future releases. Ideally, you can follow this PR rule: feature/ms-service-name/feature-name ----> feature/ms-service-name ----> develop ----> deploy/service-name.*
   - Go to your repository on GitHub.
   - Click on the "Compare & pull request" button next to your branch.
   - Review the changes and ensure they are correct.
   - Fill in some details about your changes in the body of the PR.
   - Submit your pull request.

2. **Perform a Code Review**
   - Once your pull request is opened, maintainers will review your work. Participate in the code review process by responding to comments and making any necessary revisions.

### After Your Contribution

- Stay engaged. After you've submitted your pull request, stay active in the project. Respond to feedback on your contribution.
- Look for other areas to help. Keep an eye on the repository for other areas you might contribute to.
- Share your experience. Let others know about your contributing experience. This can help the project grow.
- We are responsible for deploying your changes into production.

Thank you for contributing to our project. Your efforts are greatly appreciated!
