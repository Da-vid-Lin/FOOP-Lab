# Getting Started with Further OOP 2026

Follow these steps carefully to connect the public course repository to your
own private QMUL GitHub repository. Do not use a public fork for assessed work.

## 1. Clone the starter repository

On the public starter repository page, click the green **Code** button and copy
its HTTPS URL. Then clone it in a terminal:

```bash
git clone https://github.qmul.ac.uk/eex250/Further-OOP-26-Starter.git
cd Further-OOP-26-Starter
```

You can also clone it using IntelliJ as described in step 5.

## 2. Create an empty private repository

Sign in to [QMUL GitHub](https://github.qmul.ac.uk/) and create a new
repository owned by your QMUL account. Keep `Further-OOP-26-Starter` as the
repository-name prefix and add your QMUL username, for example
`Further-OOP-26-Starter-abc123`. Set its visibility to **Private**.

Leave all initialisation options unticked: do not add a README, `.gitignore`, or
licence. Initialising it would give it a separate history and make setup and
future updates unnecessarily difficult.

## 3. Connect the public and private repositories

In a terminal opened in the cloned directory, run:

```bash
git remote rename origin upstream
git remote add origin https://github.qmul.ac.uk/abc123/Further-OOP-26-Starter-abc123.git
git push -u origin main
```

Replace `abc123` with your QMUL username. Check the setup with:

```bash
git remote -v
```

You should see `origin` pointing to your private QMUL repository and `upstream`
pointing to the public starter repository. Always push your work to `origin`;
never try to push it to `upstream`.

## 4. Add teaching staff as collaborators

On your private repository, go to **Settings → Collaborators**, click **Add
people**, and invite your instructors:

- Simon Lucas: **eex250**
- James Goodman: **eex859**
- Roman Pretty: **ec22761**

![Add Collaborators](./images/add-collaborators.png)



## 5. Open in IntelliJ

If you used the terminal steps above, open the cloned directory with **File →
Open…**. If you have not cloned it yet, use **File → New → Project from Version
Control…** and paste the public starter URL. After cloning, complete step 3 in
IntelliJ's terminal before pushing any work.

Set the Directory to be where you want the project to be stored on your local machine.
The Directory is the location locally where the repository will be stored. If you are using a QMUL
machine do **not** put this on OneDrive as that can interfere with IntelliJ indexing processes.
Instead put this in a new directory on your G-drive (G:). This is your home drive that will follow
you to any QMUL PC.

Click **Clone** to create the local repository, then configure the two remotes
using step 3. The public repository normally does not require authentication.
The first time you push to your private repository, you may be asked to sign in
to QMUL GitHub.

![IntelliJ Clone](./images/intellij-clone.png)

Tick the **Use credential helper** box to allow Git to store your QMUL GitHub
credentials.

If access to your private repository fails, open **File → Settings → Version
Control → Git** and make sure **Use credential helper** is ticked:

![IntelliJ Clone](./images/intellij-git-credential.png)


## 6. IntelliJ settings

Ensure you are using Java 17 in **File → Project Structure**.

![Project Structure](./images/intellij-project-structure.png)

The second place is the Gradle JVM. The setting for this are under File..Settings…Build,
Execution, Deployment…Build Tools…Gradle. Set the Gradle JVM (bottom of the screenshot
below) to be the same as the version used earlier.

![Project Structure](./images/intellij-java-gradle.png)

You may need to wait a minute or two for Gradle to load in all the libraries. Once this is done
you are ready to run Hello World and start the actual lab work on the next page.

## 7. Sanity check

Run `src/main/java/hello/HelloWorld.java` to confirm setup. You can do this in several ways. The easiest is 
to navigate to the file in the Project Explorer on the left hand side of the IDE, then click the green
arrow next to the main method (highlighted with the red arrow in the diagram below):

![Run HelloWorld](./images/intellij-hello-world.png)

## 8. Receive teaching-team updates

Commit your work before incorporating an update. Then run:

```bash
git switch main
git status
git fetch upstream
git merge upstream/main
git push origin main
```

If `git status` shows uncommitted changes, commit or stash them before merging.
If Git reports a merge conflict, it means that you and the teaching team
changed the same part of a file. Resolve the marked conflicts in IntelliJ,
commit the result, and push it to `origin`. Do not use
`--allow-unrelated-histories`; repositories created using these instructions
already share the same history.
