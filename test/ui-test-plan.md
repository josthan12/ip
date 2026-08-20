# UI Test Plan

## Configuration

- Java version: `25`
- Source directory: `src/main/java`
- Main class: `ShrekAndDonkey`
- Timeout seconds: `10`
- Comparison: Exact standard output after normalizing CRLF and LF line endings. Spaces, blank lines, punctuation, and the final newline remain significant.

## Test Cases

### TC-001: Add and display all task types

**Aim:** Verify that ToDos, deadlines, and events retain their type and timing text when added, marked, and listed.

**Inputs:**
```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 1
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
   [T][ ] borrow book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Sunday)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] borrow book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] borrow book
 2.[D][ ] return book (by: Sunday)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```

### TC-002: Preserve arbitrary deadline text

**Aim:** Verify that deadline date/time information is stored and displayed as text without date parsing.

**Inputs:**
```text
deadline do homework /by no idea :-p
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
   [D][ ] do homework (by: no idea :-p)
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```
