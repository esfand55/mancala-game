# How to Run
This is a step-by-step guide how to build and run the Manccala game using Docker-compose.

Three versions of docker-compose.yml file has provided:

* 1- docker-compose.yml:
This is the simplest version providing the Mancala Game implementation.

* 2- docker-compose-zipkin.yml:
    This version adds Zipkin server for distributed tracing and Prometheus server for metrics observations. 
    You can access Grafana dashboard for live monitoring and alerting facilities. 

* 3- docker-compose-full.yml:
    This version add ELK features to above features and Kibana dashboard
     
The link to all above features provided in index page of the Game after you run any of above versions at: http://localhost

## Installation

* The Game is implemented in Java. See
   https://www.java.com/en/download/help/download_options.xml . The
   examples need to be compiled so you need to install a JDK (Java
   Development Kit). A JRE (Java Runtime Environment) is not
   sufficient. After the installation you should be able to execute
   `java` and `javac` on the command line.

* The application run in Docker Containers. You need to install Docker
  Community Edition, see https://www.docker.com/community-edition/
  . You should be able to run `docker` after the installation.


* After installing Docker you should also be able to run
  `docker-compose`. If this is not possible, you might need to install
  it separately. See https://docs.docker.com/compose/install/ .

## Build

Change to the directory `mancala-microservice` and run `./mvnw clean
install` or `mvnw.cmd clean install` (Windows). This will take a while to download the dependencies and build two 
microservices developed for this game: `mancala-api`, `mancala-web`:

A complete should look like this: [build log](build.log)  

```
[.\mancala-game\mancala-microservice] ./mvnw clean install
....
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] mancala-api                                                        [jar]
[INFO] mancala-web                                                        [jar]
[INFO] mancala-microservice                                               [pom]
[INFO]
[INFO] -----------------< com.dzone.mancalagame:mancala-api >------------------
[INFO] Building mancala-api 0.0.1-SNAPSHOT                                [1/3]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ mancala-api ---
[INFO] Deleting D:\mancala-game\mancala-microservice\mancala-api\target
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ mancala-api ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ mancala-api ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 14 source files to D:\mancala-game\mancala-microservice\mancala-api\target\classes
[INFO]
[INFO] --- spring-cloud-contract-maven-plugin:2.1.2.RELEASE:generateTests (default-generateTests) @ mancala-api ---
[INFO] Generating server tests source code for Spring Cloud Contract Verifier contract verification
[INFO] Will use contracts provided in the folder [D:\mancala-game\mancala-microservice\mancala-api\src\test\resources\contracts]
[INFO] Directory with contract is present at [D:\mancala-game\mancala-microservice\mancala-api\src\test\resources\contracts]
[INFO] Test Source directory: D:\mancala-game\mancala-microservice\mancala-api\target\generated-test-sources\contracts added.
[INFO] Using [com.dzone.mancala.game.cdc.BaseClass] as base class for test classes, [null] as base package for tests, [null] as package with base classes, base class mappings []
[INFO] Creating new class file [D:\mancala-game\mancala-microservice\mancala-api\target\generated-test-sources\contracts\com\dzone\mancala\game\cdc\ContractVerifierTest.java]
[INFO] Generated 1 test classes.
[INFO]
[INFO] --- spring-cloud-contract-maven-plugin:2.1.2.RELEASE:convert (default-convert) @ mancala-api ---
[INFO] Will use contracts provided in the folder [D:\mancala-game\mancala-microservice\mancala-api\src\test\resources\contracts]
[INFO] Copying Spring Cloud Contract Verifier contracts to [D:\mancala-game\mancala-microservice\mancala-api\target\stubs\META-INF\com.dzone.mancalagame\mancala-api\0.0.1-SNAPSHOT\contracts]. Only files matching [.*] pattern will end up in the final JAR with stubs.
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO] Converting from Spring Cloud Contract Verifier contracts to WireMock stubs mappings
[INFO]      Spring Cloud Contract Verifier contracts directory: D:\mancala-game\mancala-microservice\mancala-api\src\test\resources\contracts
[INFO] Stub Server stubs mappings directory: D:\mancala-game\mancala-microservice\mancala-api\target\stubs\META-INF\com.dzone.mancalagame\mancala-api\0.0.1-SNAPSHOT\mappings
[INFO] Creating new stub [D:\mancala-game\mancala-microservice\mancala-api\target\stubs\META-INF\com.dzone.mancalagame\mancala-api\0.0.1-SNAPSHOT\mappings\shouldReturnMancalaGameSowingPit.json]
[INFO] Creating new stub [D:\mancala-game\mancala-microservice\mancala-api\target\stubs\META-INF\com.dzone.mancalagame\mancala-api\0.0.1-SNAPSHOT\mappings\shouldReturnNewlyCreatedMancalaGame.json]
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ mancala-api ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 5 resources
[INFO] skip non existing resourceDirectory D:\mancala-game\mancala-microservice\mancala-api\target\generated-test-resources\contracts
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ mancala-api ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 5 source files to D:\mancala-game\mancala-microservice\mancala-api\target\test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ mancala-api ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.dzone.mancala.game.cdc.ContractVerifierTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 32.086 s - in com.dzone.mancala.game.cdc.ContractVerifierTest
[INFO] Running com.dzone.mancala.game.controller.KalahaGameControllerTests
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 16.164 s - in com.dzone.mancala.game.controller.KalahaGameControllerTests
[INFO] Running com.dzone.mancala.game.model.KalahaGameTests
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 s - in com.dzone.mancala.game.model.KalahaGameTests
[INFO] Running com.dzone.mancala.game.repository.KalahaGameRepositoryTests
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.094 s - in com.dzone.mancala.game.repository.KalahaGameRepositoryTests
2019-09-01 12:49:18.555  INFO [mancala-api,,,] 8692 --- [      Thread-11] c.n.l.PollingServerListUpdater           : Shutting down the Executor Pool for PollingServerListUpdater
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- spring-cloud-contract-maven-plugin:2.1.2.RELEASE:generateStubs (default-generateStubs) @ mancala-api ---
[INFO] Files matching this pattern will be excluded from stubs generation []
[INFO] Building jar: D:\mancala-game\mancala-microservice\mancala-api\target\mancala-api-0.0.1-SNAPSHOT-stubs.jar
[INFO]
[INFO] --- maven-jar-plugin:3.1.2:jar (default-jar) @ mancala-api ---
[INFO] Building jar: D:\mancala-game\mancala-microservice\mancala-api\target\mancala-api-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.1.6.RELEASE:repackage (repackage) @ mancala-api ---
[INFO] Replacing main artifact with repackaged archive
[INFO]
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ mancala-api ---
[INFO] Installing D:\mancala-game\mancala-microservice\mancala-api\target\mancala-api-0.0.1-SNAPSHOT.jar to C:\Users\Talebi\.m2\repository\com\dzone\mancalagame\mancala-api\0.0.1-SNAPSHOT\mancala-api-0.0.1-SNAPSHOT.jar
[INFO] Installing D:\mancala-game\mancala-microservice\mancala-api\pom.xml to C:\Users\Talebi\.m2\repository\com\dzone\mancalagame\mancala-api\0.0.1-SNAPSHOT\mancala-api-0.0.1-SNAPSHOT.pom
[INFO] Installing D:\mancala-game\mancala-microservice\mancala-api\target\mancala-api-0.0.1-SNAPSHOT-stubs.jar to C:\Users\Talebi\.m2\repository\com\dzone\mancalagame\mancala-api\0.0.1-SNAPSHOT\mancala-api-0.0.1-SNAPSHOT-stubs.jar
[INFO]
[INFO] -----------------< com.dzone.mancalagame:mancala-web >------------------
[INFO] Building mancala-web 0.1.0                                         [2/3]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ mancala-web ---
[INFO] Deleting D:\mancala-game\mancala-microservice\mancala-web\target
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ mancala-web ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ mancala-web ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 13 source files to D:\mancala-game\mancala-microservice\mancala-web\target\classes
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ mancala-web ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 3 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ mancala-web ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to D:\mancala-game\mancala-microservice\mancala-web\target\test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ mancala-web ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.dzone.mancala.web.client.CDCApplicationTests
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 34.345 s - in com.dzone.mancala.web.client.CDCApplicationTests
[INFO] Running com.dzone.mancala.web.client.ClientApplicationTests
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 11.549 s - in com.dzone.mancala.web.client.ClientApplicationTests
2019-09-01 12:50:23.392  INFO [mancala-web,,,] 3084 --- [      Thread-21] c.n.l.PollingServerListUpdater           : Shutting down the Executor Pool for PollingServerListUpdater
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-jar-plugin:3.1.2:jar (default-jar) @ mancala-web ---
[INFO] Building jar: D:\mancala-game\mancala-microservice\mancala-web\target\mancala-web-0.1.0.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.1.6.RELEASE:repackage (repackage) @ mancala-web ---
[INFO] Replacing main artifact with repackaged archive
[INFO]
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ mancala-web ---
[INFO] Installing D:\mancala-game\mancala-microservice\mancala-web\target\mancala-web-0.1.0.jar to C:\Users\Talebi\.m2\repository\com\dzone\mancalagame\mancala-web\0.1.0\mancala-web-0.1.0.jar
[INFO] Installing D:\mancala-game\mancala-microservice\mancala-web\pom.xml to C:\Users\Talebi\.m2\repository\com\dzone\mancalagame\mancala-web\0.1.0\mancala-web-0.1.0.pom
[INFO]
[INFO] -------------< com.dzone.mancalagame:mancala-microservice >-------------
[INFO] Building mancala-microservice 0.0.1-SNAPSHOT                       [3/3]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.0.0:clean (default-clean) @ mancala-microservice ---
[INFO]
[INFO] --- spring-boot-maven-plugin:2.0.6.RELEASE:repackage (default) @ mancala-microservice ---
[INFO]
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ mancala-microservice ---
[INFO] Installing D:\mancala-game\mancala-microservice\pom.xml to C:\Users\Talebi\.m2\repository\com\dzone\mancalagame\mancala-microservice\0.0.1-SNAPSHOT\mancala-microservice-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] mancala-api 0.0.1-SNAPSHOT ......................... SUCCESS [01:11 min]
[INFO] mancala-web 0.1.0 .................................. SUCCESS [01:04 min]
[INFO] mancala-microservice 0.0.1-SNAPSHOT ................ SUCCESS [  0.654 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:18 min
[INFO] Finished at: 2019-09-01T12:50:25+04:30
[INFO] ------------------------------------------------------------------------
```

If this does not work:

* Ensure that `settings.xml` in the directory `.m2` in your home
directory contains no configuration for a specific Maven repo. If in
doubt: delete the file.

* The tests use some ports on the local machine. Make sure that no
server runs in the background.

* Skip the tests: `./mvnw clean package -Dmaven.test.skip=true` or
  `mvnw.cmd clean package -Dmaven.test.skip=true` (Windows).

* In rare cases dependencies might not be downloaded correctly. In
  that case: Remove the directory `repository` in the directory `.m2`
  in your home directory. Note that this means all dependencies will
  be downloaded again.

## Option 1- Build and Run the containers for Mancala Game itself 

Before start building the images, please make sure you have allocated at least `4GB Memory` and `4 CPU cores` to your Docker Desktop.

First you need to build the Docker images. Change to the directory
`docker` and run `docker-compose` command as below. This will download some base
images, install software into Docker images and will therefore take
its time:

```
D:\mancala-game\docker>docker-compose -f docker-compose.yml build
consul-server uses an image, skipping
mongodb uses an image, skipping
redisdb uses an image, skipping
Building mancala-api
Step 1/4 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/4 : COPY target/mancala-api-0.0.1-SNAPSHOT.jar .
 ---> 93114635163e
Step 3/4 : CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-api-0.0.1-SNAPSHOT.jar
 ---> Running in ed68dbc2b8d5
Removing intermediate container ed68dbc2b8d5
 ---> 3b80e0b9ad1a
Step 4/4 : EXPOSE 8080
 ---> Running in c9582cf88021
Removing intermediate container c9582cf88021
 ---> 8455ae2d6a79
Successfully built 8455ae2d6a79
Successfully tagged dzone_mancala-api:latest
Building mancala-web
Step 1/4 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/4 : COPY target/mancala-web-0.1.0.jar .
 ---> bc680143debb
Step 3/4 : CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-web-0.1.0.jar
 ---> Running in 067cb6d570a6
Removing intermediate container 067cb6d570a6
 ---> 45c75ab03ea2
Step 4/4 : EXPOSE 8080
 ---> Running in 911aa3b33c9d
Removing intermediate container 911aa3b33c9d
 ---> 49aad967ccec
Successfully built 49aad967ccec
Successfully tagged dzone_mancala-web:latest
Building apache
Step 1/9 : FROM ubuntu:16.04
 ---> 5e13f8dd4c1a
Step 2/9 : RUN apt-get update ; apt-get dist-upgrade -y -qq
 ---> Using cache
 ---> d8721d2cbfe6
Step 3/9 : RUN apt-get install -y -qq apache2 &&     a2enmod proxy proxy_http proxy_ajp rewrite deflate headers proxy_balancer proxy_connect proxy_html lbmethod_byrequests &&     mkdir /var/lock/apache2 && mkdir /var/run/apache2
 ---> Using cache
 ---> 780ec58a7b43
Step 4/9 : RUN apt-get install -y -qq wget unzip &&     wget -nv https://releases.hashicorp.com/consul-template/0.18.0/consul-template_0.18.0_linux_amd64.zip &&     unzip consul-template_0.18.0_linux_amd64.zip &&     chmod a+x consul-template &&     mv consul-template /usr/bin/consul-template &&     rm consul-template_0.18.0_linux_amd64.zip
 ---> Using cache
 ---> 721c2a55adef
Step 5/9 : COPY index.html /var/www/html/index.html
 ---> Using cache
 ---> 1d71470c48ee
Step 6/9 : COPY 000-default.conf  /etc/apache2/sites-enabled/000-default.conf
 ---> Using cache
 ---> 0a29e04b4644
Step 7/9 : COPY 000-default.ctmpl /etc/apache2/sites-enabled/000-default.ctmpl
 ---> Using cache
 ---> ab2bf4764c56
Step 8/9 : EXPOSE 80
 ---> Using cache
 ---> 80d9437eb053
Step 9/9 : CMD /usr/bin/consul-template -log-level info -consul consul-server:8500 -template "/etc/apache2/sites-enabled/000-default.ctmpl:/etc/apache2/sites-enabled/000-default.conf:apache2ctl -k graceful"
 ---> Using cache
 ---> b383232216d0
Successfully built b383232216d0
Successfully tagged dzone_apache:latest
D:\mancala-game\docker>
```


Now you can start the containers using `docker-compose` command. The
`-d` option means that the containers will be started in the
background and won't output their stdout to the command line:

```
D:\mancala-game\docker>docker-compose -f docker-compose.yml up -d
Creating network "dzone_default" with the default driver
Creating dzone_mancala_redis   ... done
Creating dzone_mancala_mongodb ... done
Creating dzone_consul-server_1 ... done
Creating dzone_mancala-web_1   ... done
Creating dzone_apache_1        ... done
Creating dzone_mancala-api_1   ... done
D:\mancala-game\docker>
```

Now, you can open <http://localhost> page and play the game!
   
You can terminate all containers and deletes their respective images using `docker-compose  -f docker-compose.yml down --rmi all`.
```
Stopping dzone_mancala-api_1   ... done
Stopping dzone_apache_1        ... done
Stopping dzone_mancala-web_1   ... done
Stopping dzone_mancala_mongodb ... done
Stopping dzone_consul-server_1 ... done
Stopping dzone_mancala_redis   ... done
Removing dzone_mancala-api_1   ... done
Removing dzone_apache_1        ... done
Removing dzone_mancala-web_1   ... done
Removing dzone_mancala_mongodb ... done
Removing dzone_consul-server_1 ... done
Removing dzone_mancala_redis   ... done
Removing network dzone_default
```

## Option 2- Build and Run the containers for Mancala Game including Zipkin, Prometheus and Grafana Dashboard

Before start building the images, please make sure you have allocated at least `4GB Memory` and `4 CPU cores` to your Docker Desktop.

First you need to build the Docker images. Change to the directory
`docker` and run `docker-compose` command as below. This will download some base
images, install software into Docker images and will therefore take
its time:

```
D:\mancala-game\docker>docker-compose -f docker-compose-zipkin-cassandra.yml build
consul-server uses an image, skipping
mongodb uses an image, skipping
redisdb uses an image, skipping
zipkin-storage uses an image, skipping
zipkin-server uses an image, skipping
grafana uses an image, skipping
Building apache
Step 1/9 : FROM ubuntu:16.04
 ---> 5e13f8dd4c1a
Step 2/9 : RUN apt-get update ; apt-get dist-upgrade -y -qq
 ---> Using cache
 ---> d8721d2cbfe6
Step 3/9 : RUN apt-get install -y -qq apache2 &&     a2enmod proxy proxy_http proxy_ajp rewrite deflate headers proxy_balancer proxy_connect proxy_html lbmethod_byrequests &&     mkdir /var/lock/apache2 && mkdir /var/run/apache2
 ---> Using cache
 ---> 780ec58a7b43
Step 4/9 : RUN apt-get install -y -qq wget unzip &&     wget -nv https://releases.hashicorp.com/consul-template/0.18.0/consul-template_0.18.0_linux_amd64.zip &&     unzip consul-template_0.18.0_linux_amd64.zip &&     chmod a+x consul-template &&     mv consul-template /usr/bin/consul-template &&     rm consul-template_0.18.0_linux_amd64.zip
 ---> Using cache
 ---> 721c2a55adef
Step 5/9 : COPY index.html /var/www/html/index.html
 ---> Using cache
 ---> 1d71470c48ee
Step 6/9 : COPY 000-default.conf  /etc/apache2/sites-enabled/000-default.conf
 ---> Using cache
 ---> 0a29e04b4644
Step 7/9 : COPY 000-default.ctmpl /etc/apache2/sites-enabled/000-default.ctmpl
 ---> Using cache
 ---> ab2bf4764c56
Step 8/9 : EXPOSE 80
 ---> Using cache
 ---> 80d9437eb053
Step 9/9 : CMD /usr/bin/consul-template -log-level info -consul consul-server:8500 -template "/etc/apache2/sites-enabled/000-default.ctmpl:/etc/apache2/sites-enabled/000-default.conf:apache2ctl -k graceful"
 ---> Using cache
 ---> b383232216d0
Successfully built b383232216d0
Successfully tagged dzone_apache:latest
Building mancala-web
Step 1/4 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/4 : COPY target/mancala-web-0.1.0.jar .
 ---> Using cache
 ---> 94a7d0115585
Step 3/4 : CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-web-0.1.0.jar
 ---> Using cache
 ---> abc6d55345bd
Step 4/4 : EXPOSE 8080
 ---> Using cache
 ---> 6251fb33891b
Successfully built 6251fb33891b
Successfully tagged dzone_mancala-web:latest
Building mancala-api
Step 1/4 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/4 : COPY target/mancala-api-0.0.1-SNAPSHOT.jar .
 ---> Using cache
 ---> 320f3c213684
Step 3/4 : CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-api-0.0.1-SNAPSHOT.jar
 ---> Using cache
 ---> 593e7a1eb500
Step 4/4 : EXPOSE 8080
 ---> Using cache
 ---> 07cb21891906
Successfully built 07cb21891906
Successfully tagged dzone_mancala-api:latest
Building prometheus
Step 1/2 : FROM prom/prometheus:v2.11.1
 ---> c239578468bb
Step 2/2 : COPY prometheus.yml /etc/prometheus/
 ---> Using cache
 ---> 4b51aafe4121
Successfully built 4b51aafe4121
Successfully tagged dzone_prometheus:latest
```


Now you can start the containers using `docker-compose` command. The
`-d` option means that the containers will be started in the
background and won't output their stdout to the command line:

```
D:\mancala-game\docker>docker-compose -f docker-compose-zipkin-cassandra.yml up -d
Creating network "dzone_default" with the default driver
Creating cassandra             ... done
Creating dzone_mancala_mongodb ... done
Creating dzone_consul-server_1 ... done
Creating dzone_mancala_redis   ... done
Creating dzone_zipkin-server_1 ... done
Creating dzone_apache_1        ... done
Creating dzone_mancala-api_1   ... done
Creating dzone_mancala-web_1   ... done
Creating dzone_prometheus_1    ... done
Creating grafana               ... done

```

Now, you can open <http://localhost> page and play the game!

Note:  Please make sure you play the Game to generate metrics before accessing Zipkin or Grafana Dashboard.


1- To access Zipkin, there is a link provided at home page: <http://localhost:9411>


2- To access Prometheus dashboard and watch the health metrics of the services, there is a link provided at home page: http://localhost:9090 


3- In order to use Grafana dashboard, you need to go to <http://localhost:3000> and follow below steps: 

1- Click on `Add Datasource` and choose `Prometheus` 

2- Enter `http://localhost:9090` for the URL and `Browser` for Access 

3- Click on `Save and Test` button

4- Click on `Dashboard` tab

5- Click on `Import` button on any of given dashboards to see their live data in the home page  
 
      
You can terminate all containers and deletes their respective images using `docker-compose  -f docker-compose-zipkin-cassandra.yml down --rmi all`.
```
Stopping grafana               ... done
Stopping dzone_prometheus_1    ... done
Stopping dzone_mancala-web_1   ... done
Stopping dzone_mancala-api_1   ... done
Stopping dzone_apache_1        ... done
Stopping dzone_zipkin-server_1 ... done
Stopping dzone_mancala_mongodb ... done
Stopping dzone_mancala_redis   ... done
Stopping dzone_consul-server_1 ... done
Stopping cassandra             ... done
Removing grafana               ... done
Removing dzone_prometheus_1    ... done
Removing dzone_mancala-web_1   ... done
Removing dzone_mancala-api_1   ... done
Removing dzone_apache_1        ... done
Removing dzone_zipkin-server_1 ... done
Removing dzone_mancala_mongodb ... done
Removing dzone_mancala_redis   ... done
Removing dzone_consul-server_1 ... done
Removing cassandra             ... done
Removing network dzone_default
```


## Option 3- Build and Run the containers for Mancala Game including Zipkin, Grafana Dashboard, ELK , Kibana Dashboard

Before start building the images, please make sure you have allocated at least `4GB Memory` and `4 CPU cores` to your Docker Desktop.

First you need to build the Docker images. Change to the directory
`docker` and run `docker-compose` command as below. This will download some base
images, install software into Docker images and will therefore take
its time:

```
D:\mancala-game\docker>docker-compose -f docker-compose-full.yml build
consul-server uses an image, skipping
mongodb uses an image, skipping
redisdb uses an image, skipping
zipkin-storage uses an image, skipping
zipkin-server uses an image, skipping
node-exporter uses an image, skipping
grafana uses an image, skipping
kibana uses an image, skipping
Building apache
Step 1/9 : FROM ubuntu:16.04
 ---> 5e13f8dd4c1a
Step 2/9 : RUN apt-get update ; apt-get dist-upgrade -y -qq
 ---> Using cache
 ---> d8721d2cbfe6
Step 3/9 : RUN apt-get install -y -qq apache2 &&     a2enmod proxy proxy_http proxy_ajp rewrite deflate headers proxy_balancer proxy_connect proxy_html lbmethod_byrequests &&     mkdir /var/lock/apache2 && mkdir /var/run/apache2
 ---> Using cache
 ---> 780ec58a7b43
Step 4/9 : RUN apt-get install -y -qq wget unzip &&     wget -nv https://releases.hashicorp.com/consul-template/0.18.0/consul-template_0.18.0_linux_amd64.zip &&     unzip consul-template_0.18.0_linux_amd64.zip &&     chmod a+x consul-template &&     mv consul-template /usr/bin/consul-template &&     rm consul-template_0.18.0_linux_amd64.zip
 ---> Using cache
 ---> 721c2a55adef
Step 5/9 : COPY index.html /var/www/html/index.html
 ---> Using cache
 ---> 1d71470c48ee
Step 6/9 : COPY 000-default.conf  /etc/apache2/sites-enabled/000-default.conf
 ---> Using cache
 ---> 0a29e04b4644
Step 7/9 : COPY 000-default.ctmpl /etc/apache2/sites-enabled/000-default.ctmpl
 ---> Using cache
 ---> ab2bf4764c56
Step 8/9 : EXPOSE 80
 ---> Using cache
 ---> 80d9437eb053
Step 9/9 : CMD /usr/bin/consul-template -log-level info -consul consul-server:8500 -template "/etc/apache2/sites-enabled/000-default.ctmpl:/etc/apache2/sites-enabled/000-default.conf:apache2ctl -k graceful"
 ---> Using cache
 ---> b383232216d0
Successfully built b383232216d0
Successfully tagged dzone_apache:latest
Building mancala-web
Step 1/4 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/4 : COPY target/mancala-web-0.1.0.jar .
 ---> Using cache
 ---> 94a7d0115585
Step 3/4 : CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-web-0.1.0.jar
 ---> Using cache
 ---> abc6d55345bd
Step 4/4 : EXPOSE 8080
 ---> Using cache
 ---> 6251fb33891b
Successfully built 6251fb33891b
Successfully tagged dzone_mancala-web:latest
Building mancala-api
Step 1/4 : FROM openjdk:8-jre-alpine
 ---> f7a292bbb70c
Step 2/4 : COPY target/mancala-api-0.0.1-SNAPSHOT.jar .
 ---> Using cache
 ---> 320f3c213684
Step 3/4 : CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-api-0.0.1-SNAPSHOT.jar
 ---> Using cache
 ---> 593e7a1eb500
Step 4/4 : EXPOSE 8080
 ---> Using cache
 ---> 07cb21891906
Successfully built 07cb21891906
Successfully tagged dzone_mancala-api:latest
Building prometheus
Step 1/2 : FROM prom/prometheus:v2.11.1
v2.11.1: Pulling from prom/prometheus
ff5eadacfa0b: Pull complete
aa7f2ab6505e: Pull complete
63bc9490f8ee: Pull complete
c7f137be30ec: Pull complete
173cf5cf8d4d: Pull complete
d36aa96ac3ce: Pull complete
74879fef6949: Pull complete
8628d679a785: Pull complete
fc29e6925457: Pull complete
Digest: sha256:8f34c18cf2ccaf21e361afd18e92da2602d0fa23a8917f759f906219242d8572
Status: Downloaded newer image for prom/prometheus:v2.11.1
 ---> c239578468bb
Step 2/2 : COPY prometheus.yml /etc/prometheus/
 ---> 4b51aafe4121
Successfully built 4b51aafe4121
Successfully tagged dzone_prometheus:latest
Building elasticsearch
Step 1/5 : FROM docker.elastic.co/elasticsearch/elasticsearch:6.3.1
6.3.1: Pulling from elasticsearch/elasticsearch
7dc0dca2b151: Pull complete
d781ed11f72a: Pull complete
1750e875cdfc: Pull complete
c41f251a2369: Pull complete
75f1d1b20ebc: Pull complete
7a5561323db1: Pull complete
ee76915fb2ed: Pull complete
6df425d0ed88: Pull complete
Digest: sha256:178051b116c91ae525369f3468aec167fb2c1cd90456e86c717cb1d135b8595e
Status: Downloaded newer image for docker.elastic.co/elasticsearch/elasticsearch:6.3.1
 ---> fa7212eab151
Step 2/5 : COPY elasticsearch.in.sh /usr/share/elasticsearch/bin/elasticsearch.in.sh
 ---> ce1cb07e0359
Step 3/5 : USER root
 ---> Running in 62a9771c5eb0
Removing intermediate container 62a9771c5eb0
 ---> 28ffb9989d15
Step 4/5 : RUN chown elasticsearch:elasticsearch /usr/share/elasticsearch/bin/elasticsearch.in.sh
 ---> Running in f8df9bb971b9
Removing intermediate container f8df9bb971b9
 ---> cf683fbb2e7d
Step 5/5 : USER elasticsearch
 ---> Running in c5272229477d
Removing intermediate container c5272229477d
 ---> 916234bc983a
Successfully built 916234bc983a
Successfully tagged dzone_elasticsearch:latest
Building filebeat
Step 1/5 : FROM docker.elastic.co/beats/filebeat:6.3.1
6.3.1: Pulling from beats/filebeat
7dc0dca2b151: Already exists
561df1811f53: Pull complete
c195fb3e2a82: Pull complete
fefbffe0b72a: Pull complete
09f1ad401f77: Pull complete
6d182dd385ce: Pull complete
53a96137235f: Pull complete
Digest: sha256:339ffde106ae930b00afd9fb9feb91fc9643de8257df9c68b4bc1a88ecf5e2f2
Status: Downloaded newer image for docker.elastic.co/beats/filebeat:6.3.1
 ---> 34f48cd3b7ba
Step 2/5 : COPY filebeat.yml /usr/share/filebeat/filebeat.yml
 ---> cb102d7a5617
Step 3/5 : USER root
 ---> Running in b58e86d9dec7
Removing intermediate container b58e86d9dec7
 ---> 629ccb71d5be
Step 4/5 : RUN chown filebeat /usr/share/filebeat/filebeat.yml
 ---> Running in ccc2c6604814
Removing intermediate container ccc2c6604814
 ---> 9cb68b574bd4
Step 5/5 : USER filebeat
 ---> Running in a62c79b40a2e
Removing intermediate container a62c79b40a2e
 ---> 2a2d25e9b8fc
Successfully built 2a2d25e9b8fc
Successfully tagged dzone_filebeat:latest
```


Now you can start the containers using `docker-compose` command. The
`-d` option means that the containers will be started in the
background and won't output their stdout to the command line:

```
D:\mancala-game\docker>docker-compose  -f docker-compose-full.yml up -d
Creating network "dzone_default" with the default driver
Creating volume "dzone_logs" with default driver
Pulling zipkin-storage (openzipkin/zipkin-cassandra:)...
latest: Pulling from openzipkin/zipkin-cassandra
e8d8785a314f: Pull complete
e005d777a298: Pull complete
42d6a3b7295a: Pull complete
3e010093287c: Pull complete
a25f03576cf0: Pull complete
8edf7d75128f: Pull complete
8b45ebcad2b9: Pull complete
6af9e6161350: Pull complete
Digest: sha256:bd4967fc50cf82fb371af1428affcd42b028569ba01fdc4c82b744609b9e5a65
Status: Downloaded newer image for openzipkin/zipkin-cassandra:latest
Pulling zipkin-server (openzipkin/zipkin:)...
latest: Pulling from openzipkin/zipkin
e8d8785a314f: Already exists
e005d777a298: Already exists
42d6a3b7295a: Already exists
3e010093287c: Already exists
a25f03576cf0: Already exists
012db2144b2e: Pull complete
ddf3f4472caa: Pull complete
c8b2b2373ac0: Pull complete
Digest: sha256:1e98b332fbcf5cf547b6e04f8e6ab54e9856259a735c36edbfc64147b1de2469
Status: Downloaded newer image for openzipkin/zipkin:latest
Pulling node-exporter (prom/node-exporter:latest)...
latest: Pulling from prom/node-exporter
49a2d53aa1af: Pull complete
3589a6efd9ce: Pull complete
190160031744: Pull complete
Digest: sha256:a2f29256e53cc3e0b64d7a472512600b2e9410347d53cdc85b49f659c17e02ee
Status: Downloaded newer image for prom/node-exporter:latest
Pulling grafana (grafana/grafana:6.2.5)...
6.2.5: Pulling from grafana/grafana
fc7181108d40: Pull complete
b3698e2f9d08: Pull complete
13ca9094bc72: Pull complete
8412253947ed: Pull complete
8efa19c8dbaf: Pull complete
afa031bd3fdb: Pull complete
Digest: sha256:ab415470454ba13b2a12d8f739646ef95a76ea123047f797a048adf11d41bddf
Status: Downloaded newer image for grafana/grafana:6.2.5
Pulling kibana (docker.elastic.co/kibana/kibana:6.3.1)...
6.3.1: Pulling from kibana/kibana
7dc0dca2b151: Already exists
679c705504f2: Pull complete
8d306bb7945d: Pull complete
117569e19a28: Pull complete
f3544a3cfd4f: Pull complete
da2ecc58addb: Pull complete
b1b3b4d468ad: Pull complete
676f6309f0ec: Pull complete
06d18c47fd0f: Pull complete
Digest: sha256:847ca40b2405032de4f3d39796f9f493209d208f4baf99dfff09417fdb4071f9
Status: Downloaded newer image for docker.elastic.co/kibana/kibana:6.3.1
Creating cassandra             ... done
Creating dzone_elasticsearch_1 ... done
Creating dzone_node-exporter_1 ... done
Creating dzone_mancala_mongodb ... done
Creating dzone_mancala_redis   ... done
Creating dzone_consul-server_1 ... done
Creating dzone_zipkin-server_1 ... done
Creating dzone_apache_1        ... done
Creating dzone_kibana_1        ... done
Creating dzone_filebeat_1      ... done
Creating dzone_mancala-web_1   ... done
Creating dzone_mancala-api_1   ... done
Creating dzone_prometheus_1    ... done
Creating grafana               ... done
```

Now, you can open <http://localhost> page and play the game!

To use Kibana, you will need to follow below steps to configure it:

`Note`: Please make sure you start the Game in order to send data to Zipkin server before you try Kibana.  

1- open the page <http://localhost:5601/>

2- Go to `Management` section and select `Indexing patterns` from `Kibana`

3- provide `*` for index pattern under `Step 1 of 2: Define index pattern` and click `Next` 

4- select `timestamp_mills` from Time filter under `Step 2 of 2: Configure settings` and click `Create Index Pattern`

To see logs from sowing Endpoint, try searching the word: `playerTurn` in Kibana and see the logs 

   
You can terminate all containers and deletes their respective images using `docker-compose  -f docker-compose.yml down --rmi all`.
```
Stopping grafana               ... done
Stopping dzone_prometheus_1    ... done
Stopping dzone_mancala-api_1   ... done
Stopping dzone_mancala-web_1   ... done
Stopping dzone_filebeat_1      ... done
Stopping dzone_kibana_1        ... done
Stopping dzone_apache_1        ... done
Stopping dzone_zipkin-server_1 ... done
Stopping dzone_elasticsearch_1 ... done
Stopping dzone_mancala_mongodb ... done
Stopping dzone_consul-server_1 ... done
Stopping dzone_mancala_redis   ... done
Stopping dzone_node-exporter_1 ... done
Stopping cassandra             ... done
Removing grafana               ... done
Removing dzone_prometheus_1    ... done
Removing dzone_mancala-api_1   ... done
Removing dzone_mancala-web_1   ... done
Removing dzone_filebeat_1      ... done
Removing dzone_kibana_1        ... done
Removing dzone_apache_1        ... done
Removing dzone_zipkin-server_1 ... done
Removing dzone_elasticsearch_1 ... done
Removing dzone_mancala_mongodb ... done
Removing dzone_consul-server_1 ... done
Removing dzone_mancala_redis   ... done
Removing dzone_node-exporter_1 ... done
Removing cassandra             ... done
Removing network dzone_default
```

