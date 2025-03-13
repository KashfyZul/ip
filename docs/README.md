# Meow User Guide

![img.png](img.png)

Meow is a fun and intuitive task management system to help you keep track of your ToDos, Events and Deadlines!

# Adding Tasks
## Adding Deadlines: 
### `deadline {taskName} /by {deadline}`

Adds a task of the "Deadline" type. 

This task must contain: 
- taskName
- `deadline`: due date of this task

Example: `deadline submit assignment /by 11pm`

![img_1.png](img_1.png)

## Adding Events: 
### `event {taskName} /from {start time/day/date} / to {end time/day/date}`

Adds a task of the "Event" type.

This task must contain:
- taskName
- `start time/day/date`: the starting time of this event
- `end time/day/date`: the ending time of this event

Example: `event CS2113 Lecture /from 4pm /to 6pm`

![img_2.png](img_2.png)

## Adding ToDos:
### `todo {taskName}`

Adds a task of the "ToDo" type.

This task must contain:
- taskName

Example: `todo drink water`

![img_3.png](img_3.png)


## Listing Tasks
### `list`

Lists all existing tasks.

![img_4.png](img_4.png)

## Marking and Unmarking Tasks
### `mark {taskNumber}` or `unmark {taskNumber}`


This marks a specified task as done or not done. 

The `taskNumber` of the task to be marked or unmarked can be seen by using the command `list`. 

Example: `mark 1`
![img_5.png](img_5.png)

## Deleting Tasks
### `delete {taskNumber}`

This deletes a specified task from the list of existing tasks. 

The `taskNumber` of the task to be deleted can be seen by using the command `list`.

Example: `delete 4`
![img_6.png](img_6.png)

## Finding Tasks
### `find {keyword}`

This searches the list of existing tasks for tasks that match the specified keyword. 

A list of matching tasks is then printed.

Example: `find drink`
![img_7.png](img_7.png)

## Exiting Program
### `add tuna`

Closes the program. 

![img_8.png](img_8.png)