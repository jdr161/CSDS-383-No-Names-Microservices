import utils.FormatUtils;

import java.text.ParseException;
import java.util.Scanner;

public class CliInputHandlerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts and validates user input for a UUID upon Event or Participant creation
     * @param promptMessage message to prompt user for input
     * @param obj Event or Participant instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleUuidCreationInput(String promptMessage, Object obj) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(promptMessage);
                String uuidInput = scanner.nextLine();
                if (isCancelRequest(uuidInput)) {
                    return CLICode.MAIN_MENU;
                }
                if (uuidInput != null && !uuidInput.isBlank()) {
                    if (obj instanceof Event eventObj) {
                        eventObj.setId(UUID.fromString(uuidInput));
                    } else if (obj instanceof Participant participantObj) {
                        participantObj.setParticipantId(UUID.fromString(uuidInput));
                    }
                }
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Input must be a valid UUID. Try again");
            }
        }
        return CLICode.CONTINUE;
    }

    /**
     * Prompts and validates user input for the date of a new event
     * @param event the Event instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleEventDateInput(Event event) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Set a date (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                if (MainCLI.isCancelRequest(dateInput)) {
                    return CLICode.MAIN_MENU;
                }
                event.setDate(FormatUtils.formatDate(dateInput));
                validInput = true;
            } catch (IllegalArgumentException | ParseException e) {
                System.out.println("Input must be a valid date and match the format YYYY-MM-DD or be parsable into the specified format. Try again");
            }
        }
        return CLICode.CONTINUE;
    }

    /**
     * Prompts and validates user input for the time of a new event
     * @param event the Event instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleEventTimeInput(Event event) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Set a time (HH:MM AM/PM): ");
                String timeInput = scanner.nextLine();
                if (MainCLI.isCancelRequest(timeInput)) {
                    return CLICode.MAIN_MENU;
                }
                event.setTime(FormatUtils.formatTime(timeInput));
                validInput = true;
            } catch (IllegalArgumentException | ParseException e) {
                System.out.println("Input must match the format HH:MM AM/PM or be parsable into the specified format. Try again");
            }
        }
        return CLICode.CONTINUE;
    }

    /**
     * Prompts and validates user input for the title of a new event
     * @param event the Event instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleEventTitleInput(Event event) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Set a title: ");
                String title = scanner.nextLine();
                if (MainCLI.isCancelRequest(title)) {
                    return CLICode.MAIN_MENU;
                }

                if (title == null || title.length() < 1 || title.length() > 255) {
                    throw new IllegalArgumentException("Title should be between 1 and 255 characters, inclusive. Try again");
                } else {
                    event.setTitle(title);
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return CLICode.CONTINUE;
    }

    /**
     * Prompts and validates user input for the description of a new event
     * @param event the Event instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleEventDescriptionInput(Event event) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Set a description: ");
                String description = scanner.nextLine();
                if (MainCLI.isCancelRequest(description)) {
                    return CLICode.MAIN_MENU;
                }

                if (description == null || description.length() < 1 || description.length() > 600) {
                    throw new IllegalArgumentException("Title should be between 1 and 600 characters, inclusive. Try again");
                } else {
                    event.setDescription(description);
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return CLICode.CONTINUE;
    }

    /**
     * Prompts and validates user input for an email upon Event or Participant creation
     * @param promptMessage message to prompt user for input
     * @param obj Event or Participant instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleEmailInput(String promptMessage, Object obj) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(promptMessage);
                String input = scanner.nextLine();
                if (MainCLI.isCancelRequest(input)) {
                    return CLICode.MAIN_MENU;
                }

                if (input.isBlank() || !FormatUtils.isValidEmail(input)) {
                    throw new IllegalArgumentException("Invalid email. Try again");
                } else {
                    if (obj instanceof Event eventObj) {
                        eventObj.setHostEmail(input);
                    } else if (obj instanceof Participant participantObj) {
                        participantObj.setParticipantEmail(input);
                    }
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return CLICode.CONTINUE;
    }

    /**
     * Prompts and validates user input for the name of a new participant
     * @param participant the Participant instance to modify
     * @return {@link CLICode#MAIN_MENU} if user cancels. Otherwise, {@link CLICode#CONTINUE} for successful input
     */
    public static CLICode handleParticipantNameInput(Participant participant) {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Participant Name: ");
                String input = scanner.nextLine();
                if (MainCLI.isCancelRequest(input)) {
                    return CLICode.MAIN_MENU;
                }

                if (input == null || input.length() < 1 || input.length() > 600) {
                    throw new IllegalArgumentException("Participant name should be between 1 and 600 characters, inclusive. Try again");
                } else {
                    participant.setParticipantName(input);
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return CLICode.CONTINUE;
    }
}
