/**
 * 
 * A. How we create separation between data representative of the information?
*We used Strategy pattern. We created an interface level loader that defines a behavior. We have created different classes that implements LevelLoader, 
*and define it differently and uniquely for each department.

*B. Why this separation allows to maintain the principle of the Open / Closed?
*Since there is no need to touch the existing code. You can simply create a new loader and simply use the program without changing the existing loaders.

*C. Why It also allows to maintain the Liskov Substitution Principle?
*If someone wants to use other code, he need to create in his code the same functions (override them), 
*and he doesn't need to know how the software works (behind the scenes). If he will create in his code the same functions the program will work.

*D. Why we chose to use InputStream and the String filename in the example?
*Since the input stream is more general, so we can use any type of loader, not just those that can get a string.
 */

package model.data.loadersAndSavers;

import java.io.InputStream;

import model.Level;

public interface LevelLoader {
	public Level loadLevel(InputStream in);
}
