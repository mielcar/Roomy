# Testing playground
This project has got swagger dependencies included - most effective way to test it, is to run 
RoomyApplication in your IDE and access http://localhost:8080/swagger-ui.html

# Building application
Build jar with mvn clean install

# Customer preferences repositories
I switched from in memory repository to file based customer preferences (as suggested in requirements).
File is located in resources folder, path is configurable in application.yml

# I think that there is test error in requirements specification
# Is:

Test 4
Free Premium rooms: 10
Free Economy rooms: 1
Usage Premium: 7 (EUR 1153)
Usage Economy: 1 (EUR 45)

# Should be:

Test 4
Free Premium rooms: 10
Free Economy rooms: 1
Usage Premium: 9 (EUR 1221)
Usage Economy: 1 (EUR 22)