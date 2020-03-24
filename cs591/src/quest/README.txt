## Quest Part 1
## Author: Camilla Satte
## Class: CS591 OOP

## How to run?

In your terminal locate into the directory of with these files. Type:
$ javac *.java
$ java Main

## How to play?
Users are beginning with choosing the Quest Game Mode. Then, depending on which Quest mode they picked - they are going 
to be able to configure board dimension (minimum 3x3). Users are be able to play with multiple teams, with up to 3 
number of players for each team (game turns switch rotationally). The game can be played with maximum number of 7 teams
(because there is only 7 colors implemented).  The minimum size of team is 1, the maximum is 3 (as indicated in 
assignment).

'Destination-based' mode.
Team starts at the top-left corner of the board. The goal of the team(s) is to reach bottom-right corner. Game ends when
 the first team reaches the finishing cell.

'Endless' mode.
Team(s) start at random cell. The goal is to go around cells until some of the teams decides to quit.

'Reach-level-10' mode.
Team(s) start at random cell. The goal of team is to reach level 10. Game ends when all the members of some team reaches
level 10.


Ending game:
Option 1: quit.
Option 2: play again with the same team(s) setup can be remained.
Option 3: exit to menu page.

Additional rules/modifications:
When Hero reaches level 3, they earn Mascot (aka lucky pet) that will help heroes in their fights.
I reduced damage of monsters (var damageReducer can be found in Monster class).

**I added GFG class that validates path on the generated board. If path doesn't exist, another board is generated. 
