
## Tests execution

- To run default test suite:

       mvn clean test

- To run tests specifying suite name:

        mvn clean test -Dsuite="suite name"


- To run allure maven plugin with reports:

        mvn io.qameta.allure:allure-maven:serve

### Docker

- Requirement for run remote:

>*docker run --name=chrome --rm --network=host -p 4444:4444 -p 7900:7900 --shm-size="2g" -e SE_NODE_MAX_SESSIONS=8 -e SE_NODE_OVERRIDE_MAX_SESSIONS=true -e SE_NODE_SESSION_TIMEOUT=90 -e SE_SCREEN_WIDTH=1920 -e SE_SCREEN_HEIGHT=1080 -e SE_SCREEN_DEPTH=24 -e SE_SCREEN_DPI=74 selenium/standalone-chrome*

- To run tests with remote WebDriver:

>mvn clean test -DrunRemote=true