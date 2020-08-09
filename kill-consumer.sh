#!/usr/bin/env bash

jps | grep consumer | awk  '{print $1}' | xargs kill -9

