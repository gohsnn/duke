/**
 * This class deals with making sense of the user command. Commands are processed individually and specifically.
 */

package duke.managers;

import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;

import duke.exceptions.DukeException;

public class Parser {

    public Parser() {

    }

    /**
     * Generates and returns the corresponding command according to the entire String that has been taken
     * in.
     *
     * @param c a String containing the unprocessed input command to Duke
     * @exception DukeException is thrown when there is an error with the input
     */
    public static Command parse(String c) throws DukeException {
        //assert false : "assertion was successful";
        String[] comm = c.split(" ");
        String key = comm[0];
        assert !c.isEmpty() : "No command was given to Duke!";
        if (key.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(comm[1]));
        } else if (key.equals("find")) {
            return new FindCommand(comm[1]);
        } else if (key.equals("done")) {
            assert !comm[1].isEmpty() : "No task number provided for deletion!";
            return new DoneCommand(Integer.parseInt(comm[1]));
        } else if (key.equals("list")) {
            return new ListCommand();
        } else if (key.equals("bye")) {
            return new ExitCommand();
        } else if (comm[0].equals("todo") || comm[0].equals("deadline") || comm[0].equals("event")) {
            return new AddCommand(comm);
        } else {
            throw new DukeException("Oops! I'm sorry, but I don't know what that means :(");
        }
    }
}
