#!/usr/bin/env groovy
@Library('github.com/stakater/stakater-pipeline-library@revamp') _

releaseNodeApplication {
    appName = "web"
    notifySlack = false
    gitUser = "stakater-user"
    gitEmail = "stakater@gmail.com"
    usePersonalAccessToken = true
    tokenCredentialID = 'GithubToken'
    serviceAccount = "stakater-release-jenkins"
    dockerRepositoryURL = 'docker-release.workshop.stakater.com:443'
}
