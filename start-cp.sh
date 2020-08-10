#!/usr/bin/env bash

session=spring-cloud-002
window=${session}:cp
c01=${window}.0
c02=${window}.1
p01=${window}.2
p02=${window}.3

tmux send-keys -t "$c01" C-z 'java -jar build/libs/user-consumer-0.0.1-SNAPSHOT.jar --server.port=8091' Enter
tmux send-keys -t "$c02" C-z 'java -jar build/libs/user-consumer-0.0.1-SNAPSHOT.jar --server.port=8092' Enter

tmux send-keys -t "$p01" C-z 'java -jar build/libs/user-provider-0.0.1-SNAPSHOT.jar --server.port=9001' Enter
tmux send-keys -t "$p02" C-z 'java -jar build/libs/user-provider-0.0.1-SNAPSHOT.jar --server.port=9002' Enter

tmux select-pane -t "$p02"
tmux select-window -t "$window"
tmux attach-session -t "$session"

