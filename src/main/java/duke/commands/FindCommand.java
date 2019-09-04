/**
 * This class represents a specific command of finding a particular task in Duke.
 */

package duke.commands;

import duke.exceptions.DukeException;

import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;

import duke.tasks.Task;

import java.io.IOException;

import java.util.ArrayList;

public class FindCommand extends Command {
    private Ui ui;
    private String keyword;
    private ArrayList<Task> matchedTasks = new ArrayList<>();

    /**
     * Processes the keyword that has been input into Duke chat bot.
     * @param keyword that the user has input to search all his tasks for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Searches for tasks with that specific keyword and returns all matching tasks to the user.
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.ui = ui;
        ArrayList<Task> allTasks = tasks.getAllTasks();
        for (Task task : allTasks) {
            String taskDescription = task.getDescription();
            String[] splitWords = taskDescription.split(" ");
            for (String word : splitWords) {
                if (word.trim().equals(keyword)) {
                    matchedTasks.add(task);
                    break;
                }
            }
        }
        return printAllMatches();
    }

    /**
     * Prints all the tasks that match the keyword given by the user. If there are no matching tasks,
     * the user will be advised as such.
     */
    private String printAllMatches() {
        if (matchedTasks.size() > 0) {
            String printedLines = "Here are the matching tasks in your list:" + "\n";
            int index = 1;
            for (Task task : matchedTasks) {
                printedLines += index + "." + task.toString() + "\n";
                index++;
            }
            return this.ui.printLine(printedLines.trim());
        } else {
            return this.ui.printLine("Oops, there are no tasks with that keyword!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
