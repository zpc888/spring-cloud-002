#!/usr/bin/env bash

jps | grep provider | awk  '{print $1}' | xargs kill -9

