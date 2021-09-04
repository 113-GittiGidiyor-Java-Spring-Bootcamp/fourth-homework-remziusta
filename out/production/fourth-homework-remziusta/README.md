
![homework](https://user-images.githubusercontent.com/45206582/131386439-6727321a-5a50-4c20-9413-ea4013013434.PNG)

# Instructor Path
| Request 	|                  Route                  	|                                             Body                                            	|                        Description                        	|
|:-------:	|:---------------------------------------:	|:---------------------------------------------------------------------------------------------:|:---------------------------------------------------------:	|
|   GET   	|               /instructor               	|                                            EMPTY                                            	|                    List all instructor                    	|
|   GET   	|            /instructor/{:id}            	|                                            EMPTY                                            	|                      Get a instructor                     	|
|   POST  	|               /instructor               	|      { name:":name", address:"address", phoneNumber:":phoneNumber", salary:":salary" }      	|                      Add a instructor                     	|
|   PUT   	|               /instructor               	| { id:":id", name:":name", address:"address", phoneNumber:":phoneNumber", salary:":salary" } 	|                    Update a instructor                    	|
|  DELETE 	|            /instructor/{:id}            	|                                            EMPTY                                            	|                    Delete a instructor                    	|

# Course Path
| Request 	|     Route     	|                                 Body                                 	                            |   Description   	|
|:-------:	|:-------------:	|:-------------------------------------------------------------------------------------------------:|:---------------:	|
|   GET   	|    /course    	|                                 EMPTY                                	                            | List all course 	|
|   GET   	| /course/{:id} 	|                                 EMPTY                                	                            |   Get a course  	|
|   POST  	|    /course    	|      { courseName:":courseName", courseCredit:":courseCredit", courseCode:":courseCode" }      	|   Add a course  	|
|   PUT   	|    /course    	| { id:":id" ,courseName:":courseName", courseCredit:":courseCredit", courseCode:":courseCode" } 	| Update a course 	|
|  DELETE 	| /course/{:id} 	|                                 EMPTY                                	                            | Delete a course 	|

# Student Path

| Request 	|      Route     	|                              Body                              	            |    Description   	|
|:-------:	|:--------------:	|:-----------------------------------------------------------------------------:| :----------------:|
|   GET   	|    /student    	|                              EMPTY                             	            | List all student 	|
|   GET   	| /student/{:id} 	|                              EMPTY                             	            |   Get a student  	|
|   POST  	|    /student    	|      { name:":name", gender:":gender", age:":age", address:":address" }      	|   Add a student  	|
|   PUT   	|    /student    	| { id:":id" ,name:":name", gender:":gender", age:":age", address:":address" } 	| Update a student 	|
|  DELETE 	| /student/{:id} 	|                              EMPTY                             	            | Delete a student 	|


# Log Path

| Request 	|      Route     	         |                              Body                              	            |    Description       	        |
|:-------:	|:--------------------------:|:----------------------------------------------------------------------------:| :----------------------------:|
|   GET   	|   /logs       	         |                              EMPTY                             	            | List all logs 	            |
|   GET   	|   /logs/{:status}          |                              EMPTY                             	            | Returns logs with error code 	|
|   GET   	|   /logs/created/{:date}  |  	                        EMPTY                                           | Returns logs between two dates|
