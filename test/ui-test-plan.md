# UI Test Plan

## Configuration

- Java version: `25`
- Source directory: `src/main/java`
- Main class: `ShrekAndDonkey`
- Timeout seconds: `10`
- Comparison: Exact standard output after normalizing CRLF and LF line endings. Spaces, blank lines, punctuation, and the final newline remain significant.

## Test Cases

### TC-FULL-001: Complete task workflow

**Aim:** Verify adding all task types, marking and unmarking a task, and listing polymorphic task output.

**Inputs:**
```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 1
unmark 1
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
 OK, I've marked this task as not done yet:
   [T][ ] borrow book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] return book (by: Sunday)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```

### TC-FULL-002: Preserve arbitrary timing strings

**Aim:** Verify that deadline and event timing information is stored and displayed without date parsing.

**Inputs:**
```text
deadline do homework /by no idea :-p
event orientation week /from 4/10/2019 /to 11/10/2019
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
   [D][ ] do homework (by: no idea :-p)
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] orientation week (from: 4/10/2019 to: 11/10/2019)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[D][ ] do homework (by: no idea :-p)
 2.[E][ ] orientation week (from: 4/10/2019 to: 11/10/2019)
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```

### TC-FULL-003: Reject empty descriptions

**Aim:** Verify that ToDos, deadlines, and events with empty descriptions use the custom exception-message format.

**Inputs:**
```text
todo
deadline /by Sunday
event /from Mon /to Tue
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
OOPS!!UWU description of a todo cannot be empty UwU
____________________________________________________________
____________________________________________________________
OOPS!!UWU description of a deadline cannot be empty UwU
____________________________________________________________
____________________________________________________________
OOPS!!UWU description of a event cannot be empty UwU
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```

### TC-FULL-004: Reject incomplete and unknown commands

**Aim:** Verify missing task markers and unknown input produce errors without adding tasks.

**Inputs:**
```text
deadline return book
event project meeting
blah
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
 Please specify a deadline using /by.
____________________________________________________________
____________________________________________________________
 Please specify an event using /from and /to.
____________________________________________________________
____________________________________________________________
NO VALID INPUT GIVEN,PWEASE TRY AGAIN
____________________________________________________________
____________________________________________________________
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```
