# AB3 MVP Feature Specifications (Must-Have Only)

Target User: **Rachel Lim Xuan** (Independent Life & Health Insurance Agent, 100–200 clients).

---

### 1. Add Client

* **Feature:** Add Client
* **Purpose:** Adds a new client or prospect with contact details and categorization tags.
* **Command format:** `add n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]...`
* **Example commands:**
  * `add n/John Doe p/98765432 e/johnd@example.com a/311 Clementi Ave 2 t/prospect`
  * `add n/Betsy Crowe p/81234567 e/betsy@gmail.com a/12 Orchard Rd`
* **Parameters:**
  * `n/NAME`:
    * *Acceptable values:* Non-blank string of ASCII letters, digits, and single spaces. Extra spaces trimmed. Case-preserving, but case-insensitive for uniqueness (`John` = `john`). No symbols/hyphens.
    * *Error message:* `Names should only contain alphanumeric characters and spaces, and it should not be blank`
    * *Rationale:* Prevents corrupted names and ensures clean whole-word search matching.
  * `p/PHONE`:
    * *Acceptable values:* Digits only (`0-9`), minimum 3 digits. No spaces, hyphens, or `+`.
    * *Error message:* `Phone numbers should only contain numbers, and it should be at least 3 digits long`
    * *Rationale:* Enforces valid dialable numbers and prevents non-numeric input.
  * `e/EMAIL`:
    * *Acceptable values:* Format `local-part@domain`. Local part allows alphanumeric characters and non-consecutive `+`, `_`, `.`, `-`. Domain requires valid labels and a top-level domain $\ge 2$ characters.
    * *Error message:* `Emails should be of the form local-part@domain`
    * *Rationale:* Enforces valid email syntax to prevent contact failures.
  * `a/ADDRESS`:
    * *Acceptable values:* Non-blank text. Spaces and punctuation allowed.
    * *Error message:* `Addresses can take any values, and it should not be blank`
    * *Rationale:* Accommodates diverse physical address formats.
  * `t/TAG` (Optional, 0 or more):
    * *Acceptable values:* Single alphanumeric word (no spaces or symbols). Case-sensitive.
    * *Error message:* `Tags names should be alphanumeric`
    * *Rationale:* Lightweight tagging for client classification (e.g., `prospect`, `termLife`).
* **Outputs:**
  * *Succeeds:* New card added to list panel; list resets to display all clients. Message: `New person added: NAME; Phone: PHONE; Email: EMAIL; Address: ADDRESS; Tags: [TAGS]`
  * *Fails:* Missing fields or invalid values display parameter-specific error messages.
* **Duplicate handling:**
  * *Rule:* Two clients are duplicates if they have the same name (case-insensitive).
  * *Reaction & Rationale:* Rejects with message `This person already exists in the address book.` Prevents splitting notes and policies across duplicate entries.
* **Possible errors:**
  * Missing prefix or empty field $\rightarrow$ `Invalid command format! \nadd: Adds a person...`
* **Relevant UI mock-ups:** Left list panel scrolls to the newly created contact card.

---

### 2. Edit Client

* **Feature:** Edit Client
* **Purpose:** Modifies details of an existing client without re-entering unchanged information.
* **Command format:** `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`
* **Example commands:**
  * `edit 1 p/91234567 e/newemail@example.com`
  * `edit 2 t/` (clears existing tags)
* **Parameters:**
  * `INDEX`:
    * *Acceptable values:* Positive integer ($\ge 1$) within the displayed list range.
    * *Error message:* `The person index provided is invalid`
    * *Rationale:* Identifies target item based on what user sees on screen.
  * `[n/]`, `[p/]`, `[e/]`, `[a/]`, `[t/]`: At least one required. Adhere to validation rules in `add`. Passing `t/` with empty value clears tags.
* **Outputs:**
  * *Succeeds:* Card at `INDEX` updates immediately. Message: `Edited Person: [Updated details]`
  * *Fails:* Index out of bounds $\rightarrow$ `The person index provided is invalid`; No fields $\rightarrow$ `At least one field to edit must be provided.`
* **Duplicate handling:**
  * *Rule & Reaction:* Rejects if edited name conflicts with another client in the address book (`This person already exists in the address book.`).
* **Possible errors:**
  * Editing an out-of-range index on a filtered search list $\rightarrow$ Returns invalid index error.
* **Relevant UI mock-ups:** Contact card details update in-place on the list panel.

---

### 3. Delete Client

* **Feature:** Delete Client
* **Purpose:** Permanently removes an erroneous or mistaken client entry.
* **Command format:** `delete INDEX`
* **Example commands:**
  * `delete 1`
* **Parameters:**
  * `INDEX`:
    * *Acceptable values:* Positive integer ($\ge 1$) within displayed list range.
    * *Error message:* `The person index provided is invalid`
    * *Rationale:* Prevents unintentional deletions by binding to visible index.
* **Outputs:**
  * *Succeeds:* Contact card removed; remaining cards shift up. Message: `Deleted Person: [Details]`
  * *Fails:* Index invalid or out of range $\rightarrow$ `The person index provided is invalid`
* **Duplicate handling:** Not applicable.
* **Possible errors:**
  * Deleting from an empty list $\rightarrow$ `The person index provided is invalid`.
* **Relevant UI mock-ups:** Card is removed from list view; count decrements.

---

### 4. Find Clients

* **Feature:** Find Clients
* **Purpose:** Filters the client list by keywords matching any part of a client's name.
* **Command format:** `find KEYWORD [MORE_KEYWORDS]...`
* **Example commands:**
  * `find John`
  * `find Alice David`
* **Parameters:**
  * `KEYWORD`:
    * *Acceptable values:* Non-empty string. Case-insensitive. Matches whole words only (`Alex` matches `Alex Yeoh`, not `Alexander`). Multiple keywords match with OR logic.
    * *Error message:* `Invalid command format! \nfind: Finds all persons whose names contain any of the specified keywords...`
    * *Rationale:* Fast retrieval of client profiles immediately before meetings or calls.
* **Outputs:**
  * *Succeeds:* Contact list filters to matching contacts. Message: `X persons listed!`
  * *Fails:* No keywords provided $\rightarrow$ Shows format error.
* **Duplicate handling:** Not applicable.
* **Possible errors:**
  * Substring match expected $\rightarrow$ Returns 0 matches (must match full words).
* **Relevant UI mock-ups:** List panel displays only cards matching keywords.

---

### 5. List Clients

* **Feature:** List Clients
* **Purpose:** Resets active search or reminder filters to show all clients.
* **Command format:** `list`
* **Example commands:**
  * `list`
* **Parameters:** None.
* **Outputs:**
  * *Succeeds:* Shows all clients. Message: `Listed all persons`
  * *Fails:* None.
* **Duplicate handling:** Not applicable.
* **Possible errors:** None.
* **Relevant UI mock-ups:** List panel restores full client collection.

---

### 6. Add Client Note

* **Feature:** Add Client Note
* **Purpose:** Appends timestamped notes, conversation summaries, or client preferences to a client record.
* **Command format:** `note INDEX c/NOTE_CONTENT`
* **Example commands:**
  * `note 1 c/Interested in critical illness plan; prefers WhatsApp over calls.`
* **Parameters:**
  * `INDEX`: Positive integer ($\ge 1$) within displayed list range.
  * `c/NOTE_CONTENT`:
    * *Acceptable values:* Non-empty string. Spaces and punctuation permitted.
    * *Error message:* `Note content cannot be empty. Specify note with c/NOTE_CONTENT`
    * *Rationale:* Captures critical client context post-meeting without replacing previous notes.
* **Outputs:**
  * *Succeeds:* Note appended with today's date tag. Message: `Added note to John Doe: [2026-09-16] Interested in critical illness...`
  * *Fails:* Empty note $\rightarrow$ `Note content cannot be empty.`; Invalid index $\rightarrow$ `The person index provided is invalid`
* **Duplicate handling:** Sequential log; duplicate note text is allowed.
* **Possible errors:**
  * Missing `c/` prefix $\rightarrow$ Invalid command format.
* **Relevant UI mock-ups:** Notes display in an interaction history box on the client card.

---

### 7. Schedule Follow-up

* **Feature:** Schedule Follow-up
* **Purpose:** Sets or reschedules a follow-up deadline date and actionable reason for a client.
* **Command format:** `followup INDEX d/YYYY-MM-DD r/REASON`
* **Example commands:**
  * `followup 1 d/2026-09-20 r/Call to present policy comparison`
* **Parameters:**
  * `INDEX`: Positive integer ($\ge 1$) within displayed list range.
  * `d/DATE`:
    * *Acceptable values:* Valid ISO-8601 date in `YYYY-MM-DD` format. Nonexistent dates (e.g. `2026-02-31`) rejected.
    * *Error message:* `Date must be a valid calendar date in YYYY-MM-DD format.`
    * *Rationale:* Eliminates ambiguity in date formatting and enables sorting.
  * `r/REASON`:
    * *Acceptable values:* Non-empty string.
    * *Error message:* `Follow-up reason cannot be empty.`
    * *Rationale:* Ensures context for the reminder is recorded.
* **Outputs:**
  * *Succeeds:* Follow-up badge appears on client card with date and reason. Message: `Scheduled follow-up for John Doe on 2026-09-20: Call to present policy comparison`
  * *Fails:* Invalid date format or missing fields $\rightarrow$ Parameter-specific error.
* **Duplicate handling:** Overwrites any existing follow-up for this client (rescheduling).
* **Possible errors:**
  * Past date entered $\rightarrow$ Accepted, but visually flagged as overdue immediately.
* **Relevant UI mock-ups:** Client card displays a badge: `[⏰ 2026-09-20: Call to present policy comparison]`.

---

### 8. Complete Follow-up

* **Feature:** Complete Follow-up
* **Purpose:** Clears the pending follow-up and updates the client's last-contacted date to today.
* **Command format:** `done INDEX`
* **Example commands:**
  * `done 1`
* **Parameters:**
  * `INDEX`: Positive integer ($\ge 1$) within displayed list range.
    * *Error message:* `The person index provided is invalid`
    * *Rationale:* One-word action to close tasks immediately after client interactions.
* **Outputs:**
  * *Succeeds:* Clears follow-up badge from client card; updates "Last Contacted" date to today. Message: `Completed follow-up for John Doe. Last contacted date updated to 2026-09-16.`
  * *Fails:* Client has no follow-up $\rightarrow$ `This client does not have any pending follow-up.`; Invalid index $\rightarrow$ `The person index provided is invalid`
* **Duplicate handling:** Not applicable.
* **Possible errors:**
  * Running `done` on a client with no active follow-up $\rightarrow$ Rejects with notice.
* **Relevant UI mock-ups:** Reminder badge removed; last-contacted label updates.

---

### 9. View Due Reminders

* **Feature:** View Due Reminders
* **Purpose:** Filters the client list to show only clients with follow-ups overdue or due today/this week.
* **Command format:** `reminders`
* **Example commands:**
  * `reminders`
* **Parameters:** None.
* **Outputs:**
  * *Succeeds:* List filters to clients with pending follow-ups, sorted chronologically with overdue items first. Message: `Found X clients with upcoming or overdue follow-ups.`
  * *Fails:* None.
* **Duplicate handling:** Not applicable.
* **Possible errors:**
  * No follow-ups exist $\rightarrow$ Displays `0 clients with upcoming or overdue follow-ups.`
* **Relevant UI mock-ups:** Filtered list; overdue items highlighted with a red indicator.

---

### 10. Clear Records

* **Feature:** Clear Records
* **Purpose:** Removes all client records from the application (to clear sample data on first use).
* **Command format:** `clear`
* **Example commands:**
  * `clear`
* **Parameters:** None.
* **Outputs:**
  * *Succeeds:* All records removed. Message: `Address book has been cleared!`
  * *Fails:* None.
* **Duplicate handling:** Not applicable.
* **Possible errors:** None.
* **Relevant UI mock-ups:** List panel becomes empty.

---

### 11. Help

* **Feature:** Help
* **Purpose:** Displays command syntax and link to user guide.
* **Command format:** `help`
* **Example commands:**
  * `help`
* **Parameters:** None.
* **Outputs:**
  * *Succeeds:* Opens pop-up window with user guide URL. Message: `Opened help window.`
  * *Fails:* None.
* **Duplicate handling:** Not applicable.
* **Possible errors:** None.
* **Relevant UI mock-ups:** Help modal dialog.

---

### 12. Exit

* **Feature:** Exit
* **Purpose:** Closes the application and persists all records to disk.
* **Command format:** `exit`
* **Example commands:**
  * `exit`
* **Parameters:** None.
* **Outputs:**
  * *Succeeds:* Application window closes.
  * *Fails:* None.
* **Duplicate handling:** Not applicable.
* **Possible errors:** None.
* **Relevant UI mock-ups:** Window terminates.
