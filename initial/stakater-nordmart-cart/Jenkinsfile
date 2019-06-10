#!/usr/bin/env groovy
@Library('github.com/stakater/stakater-pipeline-library@revamp') _

releaseMavenApp {
    gitUser = "stakater-user"
    gitEmail = "stakater@gmail.com"
    usePersonalAccessToken = true
    tokenCredentialID = 'GithubToken'
    appName = "cart"
    notifySlack = false
    runIntegrationTest = false
    serviceAccount = "stakater-release-jenkins"
    dockerRepositoryURL = 'docker-release.workshop.stakater.com:443'
    javaRepositoryURL = 'http://nexus.release/repository/maven'
}
