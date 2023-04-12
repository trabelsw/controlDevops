pipeline {
    environment{
    registry="faressghayer/validationcntrldevops"
    registryCredential="ea9acee5-4575-467b-8ace-764d9d54880e"
    dokerImage="examenscrum"
    }
    agent any
    stages {
        stage('Clone project from github') {
            steps {
               git branch: 'faresesghayer', url: 'https://github.com/trabelsw/controlDevops.git'
            }
        }
        stage ('maven clean') {
            steps {
                sh 'mvn clean package -e -X'
                echo 'Build stage done'
            }
        }
   
        stage("compile Project"){
            steps {
                sh 'mvn compile -X -e'
                echo 'compile stage done'
                }
        }
        stage("unit tests"){
            steps {
                 sh 'mvn test'
                  echo 'unit tests stage done'
            }
        }
         
        stage('maven package') {
             steps {
               sh 'mvn package'
          }
       }
       stage('Deploy to Nexus repo') {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: 'examenScrum', classifier: '', file: 'target/examenScrum-1.0.jar', type: 'jar']], credentialsId: '8b915797-9008-4dc7-9de9-239fe717d439', groupId: 'org.springframework.boot', nexusUrl: '192.168..19.25:8081/repository/maven-releases/', nexusVersion: 'nexus3', protocol: 'http', repository: 'nexus-deployment', version: '1.0'
            }
        }
        stage("docker build") {
            steps{
                 script {
                    dockerImage = docker.build registry +":$BUILD_NUMBER"
                    }
                }
       }
       stage("docker push") {
            steps{
                script {
                    withDockerRegistry(credentialsId: registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Cleaning up') {
            steps{
                 sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
        stage('running containers'){
            steps{
                sh 'docker-compose up -d'
            }
        }
        
    }
}