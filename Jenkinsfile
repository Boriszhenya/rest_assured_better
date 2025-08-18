pipeline {
  agent {
    docker {
      image 'maven:3.9.6-eclipse-temurin-17'
    }
  }
  stages {
    stage('build') {
      steps {
        echo "Using image: docker.io/library/maven:3.9.11-eclipse-temurin-21-alpine"
        sh 'mvn --version'
      }
    }
  }
}