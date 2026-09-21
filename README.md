# FurtherOOP25
Main sample code repo for Further OOP ECS658U Autumn 2025.

## Repository setup

This is the public, read-only starter repository. Your assessed work must be
stored in a separate **private** repository on QMUL GitHub.

Your local repository will have two remotes:

- `origin`: your private QMUL GitHub repository, where you push your work.
- `upstream`: this public starter repository, from which you receive course
  updates.

In outline:

1. Clone this public starter repository.
2. On QMUL GitHub, create a new **private, empty** repository. Do not initialise
   it with a README, `.gitignore`, or licence.
3. Change the cloned repository's remotes so the public starter is `upstream`
   and your private repository is `origin`.
4. Push to `origin` and add the Further OOP teaching staff as collaborators.

Do not use a public fork or make your assessed work public. Others could copy
your work, which may lead to an academic misconduct investigation.

The exact commands, collaborator list, and IntelliJ instructions are in
[Getting Started](labs/getting-started/README.md).

### Receiving course updates

Once the two remotes are configured, you can incorporate changes published by
the teaching team:

```bash
git switch main
git status
git fetch upstream
git merge upstream/main
git push origin main
```

Commit your own work before doing this. If `git status` shows uncommitted
changes, commit or stash them first. Git may ask you to resolve a merge conflict
when both you and the teaching team have changed the same lines; after resolving
it, commit the merge and push it to `origin`.

For the teaching team: publish corrections as normal commits on the public
repository's `main` branch. Do not rewrite or force-push released history, and
avoid changing files that students are expected to edit when a change can
instead be made elsewhere; both practices make student updates much easier to
merge.

## Your details

In your private repository, fill in your details below by editing the JSON
values:

```json
{
  "student_number": "01234567",
  "email_id": "abc123",
  "github_username": "MyGitHubName",
  "full_name": "My full name e.g. Lucas, Simon Mark"
}

```

When you have done that, commit and push to your private repository, and you are
ready to go.

First read the [submission instructions](./labs/submission.md).

Then start working through the lab scripts, starting with 
[Lab 1](labs/lab01/README.md).
See the todos in code, solve the Hello World task, test it, and when it passes
the unit test, commit and push!  Then complete the rest of the lab.
We encourage you to commit and push regularly.

## Labs

This list will be added to as they are made ready for use:

* [Lab 1](labs/lab01/README.md): Hello world, object diagram, lists intro
* [Lab 2](labs/lab02/README.md): Generic classes, type variables, iterators
* [Lab 3](./labs/lab03/README.md): Performance evaluation and stats class
* [Lab 4](./labs/lab04/README.md): Gson and Java Reflection
* [Lab 5](./labs/lab05/README.md): Intro to Java Graphics
* [Lab 6](./labs/lab06/README.md): Geometry and Shapes
