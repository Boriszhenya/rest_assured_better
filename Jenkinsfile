pipeline {
  agent {
    docker {
      image 'docker.io/library/maven:3.9.11-eclipse-temurin-21-alpine'
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