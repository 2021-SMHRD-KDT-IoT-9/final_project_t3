from flask import Flask #간단히 플라스크 서버를 만든다

import json
import urllib.request
import pymysql
import json
app = Flask(__name__)

def process_command(command):
    if command["command"] == "run_script":
        script_path = command["script_path"]
        args = command["args"]
        options = command["options"]
        # Run the script with the given arguments and options
        # ...
        return "Script executed successfully"
    elif command["command"] == "status":
        # Check the status of the application and return it
        status = "running"
        return status
    elif command["command"] == "stop":
        # Stop the application and return the status
        status = "stopped"
        return status
    else:
        # Handle unrecognized command
        return "Unrecognized command"

# ...

# Receive the JSON command from the Spring Boot server
# ...
json_command = '{"command": "run_script", "script_path": "/python/Untitled.py", "args": ["arg1", "arg2"], "options": { "option1": "value1", "option2": "value2" } }'

# Parse the JSON command into a dictionary
command = json.loads(json_command)

# Process the command
response = process_command(command)

# Send the response back to the Spring Boot server
# ...
json_response = json.dumps({"response": response})

# @app.route("/testpage")
# def spring(member_id,salon id):
#     member_id = 'bb'
#     salon_id = 'a000'

#     conn = pymysql.connect(host='project-db-stu.smhrd.com',port=3307, user='campus_b_230519_3',password='smhrd3',db='campus_b_230519_3',charset='utf8')

#     sql = f"insert into test (id, pw) values ('{member_id}','{salon_id}')"

#     cur = conn.cursor()

#     cur.execute(sql)

#     conn.commit()
    
#     return "DB저장 TEST"

# spring()

if __name__ == '__test__':
    app.run(debug=False,host="59.0.234.211/testpage",port=5000)
