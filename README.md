# Asteroids Game

Asteroids extends Game class. This class is responsible for rendering the game elements and handling user input.

The game consists of a player-controlled spaceship and randomly generated asteroids that the player must avoid. The goal of the game is to survive for as long as possible without colliding with any of the asteroids. The player starts with 5 lives.

The Asteroids class has several attributes, including the SCREEN_WIDTH and SCREEN_HEIGHT constants that define the size of the game window, as well as a Ship object and an array of Star objects. It also has several lists to keep track of bullets and asteroids.

The createRandomAsteroids method generates a list of random asteroids with varying sizes, positions, and rotations. The createShip method creates the player's spaceship.

The paint method is responsible for rendering all the game elements. It first clears the screen and draws the background. It then updates the position of all the asteroids and checks for collisions with the player's spaceship. If there is a collision, the player loses a life. It also updates the position of all the bullets and removes any that are off-screen or have collided with an asteroid. Finally, it draws all the game elements on the screen, including the player's spaceship, asteroids, stars, and bullets. The method also displays the current number of lives remaining.

Overall, this code provides a basic framework for an Asteroids game that can be expanded upon with additional features and functionality.

Here is an screenshot of the app when playing the game: ![image](https://user-images.githubusercontent.com/65461919/235214134-cd9cf679-005f-4465-b7e4-5b809e5dba3d.png)
