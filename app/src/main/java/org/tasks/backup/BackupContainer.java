package org.tasks.backup;

import com.todoroo.astrid.data.Task;

import org.tasks.data.Alarm;
import org.tasks.data.CaldavAccount;
import org.tasks.data.CaldavTask;
import org.tasks.data.Filter;
import org.tasks.data.GoogleTask;
import org.tasks.data.GoogleTaskList;
import org.tasks.data.Location;
import org.tasks.data.Tag;
import org.tasks.data.TagData;
import org.tasks.data.TaskAttachment;
import org.tasks.data.UserActivity;

import java.util.List;

import static java.util.Collections.emptyList;

public class BackupContainer {

    List<TaskBackup> tasks;
    List<TagData> tags;
    List<Filter> filters;
    List<GoogleTaskList> googleTaskLists;
    List<CaldavAccount> caldavAccounts;

    BackupContainer(List<TaskBackup> tasks, List<TagData> tags, List<Filter> filters,
                    List<GoogleTaskList> googleTaskLists, List<CaldavAccount> caldavAccounts) {
        this.tasks = tasks;
        this.tags = tags;
        this.filters = filters;
        this.googleTaskLists = googleTaskLists;
        this.caldavAccounts = caldavAccounts;
    }

    static class TaskBackup {
        Task task;
        List<Alarm> alarms;
        List<Location> locations;
        List<Tag> tags;
        List<GoogleTask> google;
        List<UserActivity> comments;
        private List<TaskAttachment> attachments;
        private List<CaldavTask> caldavTasks;

        TaskBackup(Task task, List<Alarm> alarms, List<Location> locations, List<Tag> tags,
                   List<GoogleTask> google, List<UserActivity> comments, List<TaskAttachment> attachments,
                   List<CaldavTask> caldavTasks) {
            this.task = task;
            this.alarms = alarms;
            this.locations = locations;
            this.tags = tags;
            this.google = google;
            this.comments = comments;
            this.attachments = attachments;
            this.caldavTasks = caldavTasks;
        }

        List<TaskAttachment> getAttachments() {
            return attachments == null ? emptyList() : attachments;
        }

        List<CaldavTask> getCaldavTasks() {
            return caldavTasks == null ? emptyList() : caldavTasks;
        }
    }
}
