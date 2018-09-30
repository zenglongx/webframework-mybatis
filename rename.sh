#!/bin/bash
echo 'input project name'
read project_name

echo 'delete not need file'
find . -name "*.iml" |xargs rm -rf
find . -name ".DS_Store" | xargs rm -rf
find . -name ".git" | xargs rm -rf
find . -name ".idea" | xargs rm -rf
find . -name "target" | xargs rm -rf

echo 'rename file'
find . -name webframework |xargs rename -v "s/webframework/${project_name}/g" *

echo 'rename content'
grep -rl "webframework" .|xargs sed -i "s/webframework/${project_name}/g"

echo 'finish!'