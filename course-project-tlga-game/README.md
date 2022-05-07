
COMP 127 Final Project:  Rocket Game!
==========================

<b>General Goal: </b>   
This is a game that simulates a rocket traveling through space and defeating enemy spaceships. We design to have a rocket controlled by the player that shoots at enemies, a moving background, and many enemy rockets moving down the canvas towards the player rocket. The goal of the game is to dodge around the enemies and shoot as many enemies as possible. 

<b>The Base Game: </b>   
To run the whole game, click run in the Main method in the RocketGame class. Start screen: The game starts with the rocket in the same location, no enemies on the screen, and a moving background of meteors and stars. 

<b>Movement: </b>   
The player controls the rocket by their mouse movement, similar to what we did to the paddle in homework 4. The rocket can move throughout the canvas as long as it is within the four boundary walls. 

<b>Enemy: </b>  
The enemy rockets will move at different constant speeds across the canvas from the top to the bottom. Once the enemies move off the screen, they are removed from the canvas. The number and the speed of enemy rockets on the screen are randomly generated: there will be around 0 to 10 (random.nextInt()) rockets on the screen at all times once the game begins. 

<b>Shooting: </b>   
The player’s rocket will automatically shoot out 10 ammo every second. Once the ammo (getElementAt) hits the enemy, it destroys (removes) the incoming spaceship. 

<b>Winning and Losing Mechanism: </b>  
The game will end when the player collides with an enemy. The player can get points if the ammo hits any of the enemy’s rockets; however, they won’t lose points by missing their shot. Points will be displayed on the top right corner. If the player loses the game by colliding into a rocket, everything on the canvas will be removed, the image of an explosion will show up and the background keeps moving, then it changes to the end screen, which displays “GAME OVER” and your total score. 

<b>Challenging part: </b> 
1. How to keep the background moving smoothly.  
2. How to show the score and remove rocket, enemy, and ammo at the same time while keeping the background moving.  
3. How to show the movement of ammo when the rocket is stationary.  
4. How to generate new enemies randomly.  

<b>Inspiration: </b>  
The idea came from space invaders, but we have different mechanisms. In our game, only the rocket is shooting and it can move in any direction on the canvas. Also, we got all the photos from a minigame on Wechat.

<b>Help: </b>  
We got help from preceptor Tim, Jacqueline, Hengrui, Cora, Myles, and Professor Joslenne Pena. Thanks for the advice from all our classmates during the progressive presentations!
