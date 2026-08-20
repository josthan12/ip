# UI Test Plan

## Configuration

- Java version: `25`
- Source directory: `src/main/java`
- Main class: `ShrekAndDonkey`
- Timeout seconds: `10`
- Comparison: Exact standard output after normalizing CRLF and LF line endings. Spaces, blank lines, punctuation, and the final newline remain significant.

## Test Cases

### TC-001: Exit immediately

**Aim:** Verify that the program starts and exits cleanly when the first command is `bye`.

**Inputs:**
```text
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
FEE FIE FOE FUMP. GET OUT OF MY SWAMP!
____________________________________________________________
```
