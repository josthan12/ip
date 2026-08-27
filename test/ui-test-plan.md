# UI Test Plan

## Configuration

- Java version: `25`
- Source directory: `src/main/java`
- Main class: `ShrekAndDonkey`
- Timeout seconds: `10`
- Comparison: Exact standard output after normalizing CRLF and LF line endings. Spaces, blank lines, punctuation, and the final newline remain significant.

## Test Cases

### TC-DELETE-006: Delete first, last, and only tasks

**Aim:** Verify task output remains unchanged while adding, deleting, and listing tasks.

**Inputs:**
```text
todo first
todo second
todo third
delete 1
delete 2
delete 1
list
bye
```

**Expected output:**
```text
____________________________________________________________
 ____  _              _        _              _ ____              _
/ ___|| |__  _ __ ___| | __   / \   _ __   __| |  _ \  ___  _ __ | | _____ _   _
\___ \| '_ \| '__/ _ \ |/ /  / _ \ | '_ \ / _` | | | |/ _ \| '_ \| |/ / _ \ | | |
 ___) | | | | | |  __/   <  / ___ \| | | | (_| | |_| | (_) | | | |   <  __/ |_| |
|____/|_| |_|_|  \___|_|\_\/_/   \_\_| |_|\__,_|____/ \___/|_| |_|_|\_\___|\__, |
                                                                            |___/
Grrr GET OUT OF MY SWAMP! Ohh, I didn't see you there stranger! I'm ShrekAndDonkey.
What can I do for you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] first
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] second
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] third
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Noted. I've removed this task:
   [T][ ] first
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Noted. I've removed this task:
   [T][ ] third
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Noted. I've removed this task:
   [T][ ] second
 Now you have 0 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```

### TC-DELETE-007: Reject invalid delete arguments

**Aim:** Verify validation messages are displayed without terminating the chatbot.

**Inputs:**
```text
delete abc
delete 1
bye
```

**Expected output:**
```text
____________________________________________________________
 ____  _              _        _              _ ____              _
/ ___|| |__  _ __ ___| | __   / \   _ __   __| |  _ \  ___  _ __ | | _____ _   _
\___ \| '_ \| '__/ _ \ |/ /  / _ \ | '_ \ / _` | | | |/ _ \| '_ \| |/ / _ \ | | |
 ___) | | | | | |  __/   <  / ___ \| | | | (_| | |_| | (_) | | | |   <  __/ |_| |
|____/|_| |_|_|  \___|_|\_\/_/   \_\_| |_|\__,_|____/ \___/|_| |_|_|\_\___|\__, |
                                                                            |___/
Grrr GET OUT OF MY SWAMP! Ohh, I didn't see you there stranger! I'm ShrekAndDonkey.
What can I do for you?
____________________________________________________________
____________________________________________________________
 Please enter a valid task number after 'delete'.
____________________________________________________________
____________________________________________________________
 Please enter a task number from 1 to 0.
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```
