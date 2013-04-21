ant-vix-tasks
=============

## What's this
This project aims to create a comprehensive Ant task set to manage VMWare machines via VIX API.

## Managed VMs as build/test environments
Controlling VMs from buildfile could be of great help if, for example:
* you're building project for N configurations,
* you're testing project on N configurations.

Instead of building/testing on separate hardware machines, you are able to use virtual environment powered by VMWare.

## Use it in CI environment or locally
Due to the fact that it is Ant, you are able to integrate VM operations in your regular build, which probably uses [Jenkins](http://jenkins-ci.org/) or [TeamCity](http://www.jetbrains.com/teamcity/) or another CI server which is able to work with Ant out of box.

Just copy needed files to VM, make all you need on VM, and get back the results, just in your buildfile!

To do it, either:
* install VMWare Workstation onto build agent, or
* use build agent to access remote VI Server, or VMWare Server

Also, debug it on your local machine with Workstation or even Player.

## Why not Maven
Ant was chosen over Maven because you rather want to specify a list of operations on VM and Guest, and not to use pre-defined build phases.

Though, some tries with Maven also took place, if you're interested, [it's here](https://github.com/zhuravlik/vix-maven-plugin). Though, I don't know whether I'll support and continue it.

## List of implemented tasks

See it [here](https://github.com/zhuravlik/ant-vix-tasks/wiki/List-of-Tasks).

## Start using it

To start using this taskset:
* [download](https://github.com/zhuravlik/ant-vix-tasks/blob/master/releases/ant-vix-tasks-1.2-SNAPSHOT.jar) the latest version
* read [user's guide](https://github.com/zhuravlik/ant-vix-tasks/wiki/User's-Guide).
