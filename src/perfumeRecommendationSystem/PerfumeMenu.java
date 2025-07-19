package perfumeRecommendationSystem;

import java.util.*;

/**
 * Main class for the Versace Perfume Recommendation Program.
 */
public class PerfumeMenu {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PerfumeLinkedList perfumeList = new PerfumeLinkedList();
        PerfumeBST perfumeBST = new PerfumeBST();
        loadPerfumeData(perfumeList, perfumeBST);

        boolean running = true;

        // Welcome
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("  ✨ Welcome to the Versace Perfume Recommendation Program! ✨ ");
        System.out.println("  🌸 Let’s guide you through finding the perfect fragrance! 🌸 ");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        while (running) {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║                    Main Menu                   ║");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("  [1] Recommend a Versace Perfume 📝 ");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("  [2] Search for a Perfume by Name 🔎             ");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("  [3] View All Perfumes Alphabetically 🔤      ");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("  [0] Exit Program ❌                            ");
            System.out.println("╚════════════════════════════════════════════════╝");

            System.out.print("Enter the number of your choice 😊: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    String gender = getGenderChoice(input);
                    String intensity = getIntensityChoice(input);
                    String longevity = getLongevityChoice(input);

                    List<Perfume> filtered = perfumeList.getByBasicPreferences(gender, intensity, longevity);
                if (filtered.isEmpty()) {
                    filtered = perfumeList.getClosestMatches(gender, intensity, longevity);
                }
                    if (filtered.isEmpty()) {
                        filtered = perfumeList.getClosestMatches(gender, intensity, longevity);
                    }

                    boolean backToMain = false;
                    while (!backToMain) {
                System.out.println();
                System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
                System.out.println("  Step 4: Choose Your Preferred Scent Notes 💐");
                System.out.println("  (You may pick multiple notes (e.g 1,5) 😊, or press enter to skip ⏭️)");
                System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
                        String[] topSelected = getNoteSelections(input, getUniqueNotes(filtered, "top"), "Top Notes");
                        String[] middleSelected = getNoteSelections(input, getUniqueNotes(filtered, "middle"), "Middle Notes");
                        String[] baseSelected = getNoteSelections(input, getUniqueNotes(filtered, "base"), "Base Notes");

                        List<Perfume> preciseMatches = perfumeList.filterByNotes(filtered, topSelected, middleSelected, baseSelected, true);

                        if (!preciseMatches.isEmpty()) {
                            System.out.println("\nHere are the perfumes that match all your selected notes: 🎯");
                            int count = 1;
                            for (Perfume p : preciseMatches) {
                                displayPerfumeBox(p, count++);
                                System.out.println("+-----------------------------------------------------------------------------------+");
                            }
                        } else {
                            System.out.println("\nNo perfumes found matching your selected notes. 😞");
                        }

                        while (true) {
                            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
                            System.out.println("║               What would you like to do next?                ║");
                            System.out.println("╠══════════════════════════════════════════════════════════════╣");
                            System.out.println("  [1] See similar perfumes 🔍                                ");
                            System.out.println("╠══════════════════════════════════════════════════════════════╣");
                            System.out.println("  [2] Choose different notes 🔁");
                            System.out.println("╠══════════════════════════════════════════════════════════════╣");
                            System.out.println("  [3] Return to Main Menu 🏠");
                            System.out.println("╠══════════════════════════════════════════════════════════════╣");
                            System.out.println("  [0] Exit Program ❌");
                            System.out.println("╚══════════════════════════════════════════════════════════════╝");
                            System.out.print("Enter your choice: ");
                            String subChoice = input.nextLine().trim();

                            switch (subChoice) {
                            case "1" -> {
                                List<Perfume> looseMatches = perfumeList.filterByNotes(filtered, topSelected, middleSelected, baseSelected, false);
                                if (!looseMatches.isEmpty()) {
                                    System.out.println("\nHere are some similar perfumes with shared notes: ✨");
                                    int count = 1;
                                    for (Perfume p : looseMatches) {
                                        displayPerfumeBox(p, count++);
                                        System.out.println("+-----------------------------------------------------------------------------------+");
                                    }
                                } else {
                                    System.out.println("\nStill no perfumes found with similar notes. 😢");
                                }
                                break;
                            }
                            case "2" -> {
                                break; // break to re-select notes
                            }
                            case "3" -> {
                                break; // break to return to main menu
                            }
                            case "0" -> {
                                System.out.println("\n╔═════════════════════════════════════════════════════════════════════╗");
                                System.out.println("  ✨Thank you for using the Versace Perfume Recommendation Program!✨");
                                System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
                                System.exit(0);
                            }
                            default -> System.out.println("⚠️ Invalid choice ⚠️ Please enter 1, 2, 3, or 0.");
                        }

                            if (subChoice.equals("2")) {
                                break; // go back to note selection
                            }
                            if (subChoice.equals("3")) {
                                backToMain = true;
                                break;
                            }
                        }
                    }
                }

                case "2" -> {
                    System.out.print("Enter the perfume name to search 🔎: ");
                    String name = input.nextLine().trim();
                    Perfume found = perfumeBST.searchByName(name);
                    if (found != null) {
                        displayPerfumeBox(found, 1);
                    } else {
                        System.out.println("No perfume found with that name. ☹️");
                    }
                }

                case "3" -> {
                    List<Perfume> sorted = perfumeBST.getAlphabeticallySortedList();
                    int count = 1;
                    for (Perfume p : sorted) {
                        displayPerfumeBox(p, count++);
                        System.out.println();
                    }
                }

                case "0" -> {
                    running = false;
                    System.out.println("\n╔═════════════════════════════════════════════════════════════════════╗");
                    System.out.println("  ✨Thank you for using the Versace Perfume Recommendation Program!✨");
                    System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
                }

                default -> System.out.println("⚠️ Invalid choice ⚠️ Please enter 1, 2, 3, or 0.");
            }
        }
        input.close();
    }



	private static String getGenderChoice(Scanner input) {
	    System.out.println();
	    System.out.println("╔══════════════════════════════╗");
	    System.out.println("║     Step 1: Select Gender    ║");
	    System.out.println("╠══════════════════════════════╣");
	    System.out.println("  [1] Female 🙋‍♀️              ");
	    System.out.println("╠══════════════════════════════╣");
	    System.out.println("  [2] Male 🙋‍♂️                ");
	    System.out.println("╚══════════════════════════════╝");

	    while (true) {
	        System.out.print("Enter the number of your choice 😊: ");
	        try {
	            int choice = Integer.parseInt(input.nextLine());
	            if (choice == 1) return "Female";
	            if (choice == 2) return "Male";
	            System.out.println("Please enter either 1 or 2.");
	        } catch (NumberFormatException e) {
	            System.out.println("⚠️ Invalid input ⚠️ Please enter a number (1 or 2).");
	        }
	    }
	}
	private static String getIntensityChoice(Scanner input) {
	    System.out.println();
	    System.out.println("╔═════════════════════════════════════════╗");
	    System.out.println("║ Step 2: Select Preferred Intensity      ║");
	    System.out.println("╠═════════════════════════════════════════╣");
	    System.out.println("  [1] Soft 🟢");
	    System.out.println("╠═════════════════════════════════════════╣");
	    System.out.println("  [2] Moderate 🟡");
	    System.out.println("╠═════════════════════════════════════════╣");
	    System.out.println("  [3] Strong 🟠");
	    System.out.println("╠═════════════════════════════════════════╣");
	    System.out.println("  [4] Very Strong 🔴");
	    System.out.println("╚═════════════════════════════════════════╝");

	    String[] options = {"Soft", "Moderate", "Strong", "Very Strong"};
	    while (true) {
	        System.out.print("Enter the number of your choice 😊: ");
	        try {
	            int choice = Integer.parseInt(input.nextLine());
	            if (choice >= 1 && choice <= 4) return options[choice - 1];
	            System.out.println("Please enter a number between 1 and 4.");
	        } catch (NumberFormatException e) {
	            System.out.println("⚠️ Invalid input ⚠️ Please enter a number (1 to 4).");
	        }
	    }
	}
	private static String getLongevityChoice(Scanner input) {
	    System.out.println();
	    System.out.println("╔══════════════════════════════════════════════╗");
	    System.out.println("║ Step 3: Choose Preferred Longevity           ║");
	    System.out.println("╠══════════════════════════════════════════════╣");
	    System.out.println("  [1] Moderate (4–6 hrs) ⏳");
	    System.out.println("╠══════════════════════════════════════════════╣");
	    System.out.println("  [2] Long-lasting (8+ hrs) ⏳");
	    System.out.println("╚══════════════════════════════════════════════╝");

	    while (true) {
	        System.out.print("Enter the number of your choice 😊: ");
	        try {
	            int choice = Integer.parseInt(input.nextLine());
	            if (choice == 1) return "Moderate";
	            if (choice == 2) return "Long-lasting";
	            System.out.println("Please enter either 1 or 2.");
	        } catch (NumberFormatException e) {
	            System.out.println("⚠️ Invalid input ⚠️ Please enter 1 or 2.");
	        }
	    }
	}
	private static String[] getNoteSelections(Scanner input, List<String> options, String title) {
	    if (options.isEmpty()) return new String[0];

	    while (true) {
	        String formattedTitle = String.format("%-25s", title); // pad with spaces to 25 characters
	        System.out.println("\n╔═══════════════════════════╗");
	        System.out.printf ("║ %-25s ║\n", formattedTitle);
	        System.out.println(  "╚═══════════════════════════╝");

	        for (int i = 0; i < options.size(); i++) {
	            System.out.printf("║ [%-2d] %-20s ║\n", i + 1, options.get(i));
	        }
	        System.out.println("╚═══════════════════════════╝");
	        System.out.printf("Enter numbers for %s (e.g. 1,3) 😊, or press Enter to skip ⏭️): ", title);
	        String inputLine = input.nextLine();

	        if (inputLine.isEmpty()) return new String[0];

	        String[] nums = inputLine.split(",");
	        List<String> selected = new ArrayList<>();
	        boolean valid = true;

	        for (String num : nums) {
	            try {
	                int index = Integer.parseInt(num.trim()) - 1;
	                if (index >= 0 && index < options.size()) {
	                    selected.add(options.get(index));
	                } else {
	                    valid = false;
	                    break;
	                }
	            } catch (NumberFormatException e) {
	                valid = false;
	                break;
	            }
	        }

	        if (valid) {
	            return selected.toArray(new String[0]);
	        } else {
	            System.out.printf("⚠️ Invalid selection ⚠️ Please choose numbers between 1 and %d.\n", options.size());
	        }
	    }
	}

    /**
     * Displays a formatted list of perfumes in a table.
     */
    public static void displayPerfumeBox(Perfume perfume, int number) {
        // Join notes into readable strings
        String top = String.join(", ", perfume.getTopNotes());
        String middle = String.join(", ", perfume.getMiddleNotes());
        String base = String.join(", ", perfume.getBaseNotes());

        // Boxed layout for a single perfume
        System.out.printf("🎀 Versace Perfume %d 🎀\n", number);
        System.out.println("╔══════════════════════╦═══════════════════════════════════════════════════════════╗");
        System.out.printf ("║ Name                 ║ %-57s ║\n", perfume.getName());
        System.out.println("╠══════════════════════╣═══════════════════════════════════════════════════════════╣");
        System.out.printf ("║ Gender               ║ %-57s ║\n", perfume.getGender());
        System.out.println("╠══════════════════════╣═══════════════════════════════════════════════════════════╣");
        System.out.printf ("║ Intensity            ║ %-57s ║\n", perfume.getIntensity());
        System.out.println("╠══════════════════════╣═══════════════════════════════════════════════════════════╣");
        System.out.printf ("║ Longevity            ║ %-57s ║\n", perfume.getLongevity());
        System.out.println("╠══════════════════════╣═══════════════════════════════════════════════════════════╣");
        System.out.printf ("║ Top Notes            ║ %-57s ║\n", top);
        System.out.println("╠══════════════════════╣═══════════════════════════════════════════════════════════╣");
        System.out.printf ("║ Middle Notes         ║ %-57s ║\n", middle);
        System.out.println("╠══════════════════════╣═══════════════════════════════════════════════════════════╣");
        System.out.printf ("║ Base Notes           ║ %-57s ║\n", base);
        System.out.println("╚══════════════════════╩═══════════════════════════════════════════════════════════╝");
    }
    /**
     * Returns a unique list of notes based on type (top, middle, base).
     */
    private static List<String> getUniqueNotes(List<Perfume> perfumes, String type) {
        Set<String> notes = new LinkedHashSet<>();
        for (Perfume p : perfumes) {
            String[] toAdd = switch (type) {
                case "top" -> p.getTopNotes();
                case "middle" -> p.getMiddleNotes();
                case "base" -> p.getBaseNotes();
                default -> new String[0];
            };
            Collections.addAll(notes, toAdd);
        }
        return new ArrayList<>(notes);
    }

    /**
     * Loads sample perfume data into the linked list and BST.
     */
    private static void loadPerfumeData(PerfumeLinkedList list, PerfumeBST bst) {
        // Create perfumes
        Perfume[] perfumes = {
                new Perfume("Bright Crystal", "Female", 
                        new String[]{"Yuzu", "Pomegranate", "Ice accord"}, 
                        new String[]{"Magnolia", "Lotus", "Peony"}, 
                        new String[]{"Musk", "Mahogany", "Amber"}, 
                        "Moderate", "Moderate"),
                
                new Perfume("Eros", "Male", 
                        new String[]{"Mint", "Green apple", "Lemon"}, 
                        new String[]{"Tonka bean", "Ambroxan", "Geranium"}, 
                        new String[]{"Vanilla", "Vetiver", "Cedar", "Oakmoss"}, 
                        "Strong", "Long-lasting"),
                
                new Perfume("Dylan Blue", "Male", 
                        new String[]{"Water notes", "Calabrian bergamot", "Grapefruit"}, 
                        new String[]{"Black pepper", "Papyrus", "Ambrox", "Patchouli"}, 
                        new String[]{"Musk", "Saffron", "Tonka bean", "Incense"}, 
                        "Moderate", "Long-lasting"),
                
                new Perfume("Versace Pour Homme", "Male", 
                        new String[]{"Lemon", "Bergamot", "Neroli", "Rose de Mai"}, 
                        new String[]{"Hyacinth", "Clary sage", "Cedar", "Geranium"}, 
                        new String[]{"Tonka bean", "Musk", "Amber"}, 
                        "Moderate", "Moderate"),
                
                new Perfume("Yellow Diamond", "Female", 
                        new String[]{"Citron", "Pear", "Bergamot", "Neroli"}, 
                        new String[]{"Orange blossom", "Freesia", "Mimosa", "Water lily"}, 
                        new String[]{"Amber", "Musk", "Guaiac wood"}, 
                        "Soft", "Moderate"),
                
                new Perfume("Versace Man Eau Fraiche", "Male", 
                        new String[]{"Lemon", "Bergamot", "Rosewood", "Rose"}, 
                        new String[]{"Cedar", "Tarragon", "Sage", "Pepper"}, 
                        new String[]{"Musk", "Amber", "Saffron", "Woody notes"}, 
                        "Soft", "Moderate"),
                
                new Perfume("Crystal Noir", "Female", 
                        new String[]{"Cardamom", "Pepper", "Ginger"}, 
                        new String[]{"Gardenia", "Peony", "Orange blossom"}, 
                        new String[]{"Amber", "Musk", "Sandalwood"}, 
                        "Strong", "Long-lasting"),
                
                new Perfume("$4", "Female", 
                        new String[]{"Bergamot", "Green mandarin", "Fig"}, 
                        new String[]{"Sea lily", "Jasmine", "Cardamom"}, 
                        new String[]{"Sandalwood", "Cedar", "Olive wood", "Musk"}, 
                        "Moderate", "Moderate"),
                
                new Perfume("Blue Jeans", "Male", 
                        new String[]{"Bergamot", "Rose", "Anise"}, 
                        new String[]{"Lavender", "Geranium", "Sage", "Rose", "Carnation"}, 
                        new String[]{"Vanilla", "Sandalwood", "Iris", "Vetiver"}, 
                        "Moderate", "Moderate"),
                
                new Perfume("The Dreamer", "Male", 
                        new String[]{"Lavender", "Mandarin", "Juniper"}, 
                        new String[]{"Tobacco", "Iris", "Flax flower"}, 
                        new String[]{"Tonka bean", "Amber", "Fir"}, 
                        "Moderate", "Long-lasting"),
                
                new Perfume("Vanitas", "Female", 
                        new String[]{"Lime", "Freesia"}, 
                        new String[]{"Tiare flower", "Osmanthus"}, 
                        new String[]{"Cedarwood", "Tonka bean", "Vanilla"}, 
                        "Soft", "Moderate"),
                
                new Perfume("Oud Noir", "Male", 
                        new String[]{"Bitter orange", "Cardamom", "Neroli"}, 
                        new String[]{"Saffron", "Oud wood", "Olibanum"}, 
                        new String[]{"Patchouli", "Leather", "Sandalwood"}, 
                        "Very Strong", "Long-lasting"),
                new Perfume("Versace Woman", "Female",
                        new String[]{"Lemon", "Bergamot", "Orange", "Rose"},
                        new String[]{"Jasmine", "Orchid", "Cedar wood"},
                        new String[]{"Amber", "Musk", "Sandalwood"},
                        "Moderate", "Moderate"),

                new Perfume("Versace Dylan Blue Pour Femme", "Female",
                        new String[]{"Blackcurrant", "Granny Smith Apple", "Clover"},
                        new String[]{"Rose", "Jasmine", "Peach", "Petalia"},
                        new String[]{"Styrax", "White Woods", "Musk", "Patchouli"},
                        "Moderate", "Moderate"),

                new Perfume("Versace Dylan Blue Pour Homme", "Male",
                        new String[]{"Calabrian Bergamot", "Grapefruit", "Fig Leaves", "Aquatic Notes"},
                        new String[]{"Black Pepper", "Papyrus", "Patchouli", "Violet Leaf"},
                        new String[]{"Musk", "Mineral Amber", "Saffron", "Tonka Bean"},
                        "Strong", "Long-lasting"),

                new Perfume("Versace Eros Pour Femme", "Female",
                        new String[]{"Sicilian Lemon", "Bergamot", "Pomegranate"},
                        new String[]{"Lemon Blossom", "Peony", "Jasmine Sambac"},
                        new String[]{"Sandalwood", "Ambrox", "Musk", "Woodsy Notes"},
                        "Strong", "Long-lasting"),

                new Perfume("Versace L’Homme", "Male",
                        new String[]{"Lemon", "Basil", "Pimento", "Bergamot", "Petitgrain"},
                        new String[]{"Cinnamon", "Cedar", "Patchouli", "Sandalwood", "Rose", "Jasmine"},
                        new String[]{"Leather", "Oakmoss", "Vanilla", "Amber", "Musk"},
                        "Moderate", "Moderate"),

                new Perfume("Versace Red Jeans", "Female",
                        new String[]{"Peach", "Apricot", "Freesia", "Blackcurrant"},
                        new String[]{"Rose", "Violet", "Lily of the Valley", "Ylang-Ylang"},
                        new String[]{"Vanilla", "Musk", "Sandalwood"},
                        "Soft", "Moderate"),

                new Perfume("Versace Blonde", "Female",
                        new String[]{"Bergamot", "Violet", "Neroli", "Tuberose"},
                        new String[]{"Carnation", "Jasmine", "Ylang-Ylang", "Rose", "Narcissus"},
                        new String[]{"Civet", "Musk", "Vetiver", "Vanilla", "Sandalwood"},
                        "Very Strong", "Long-lasting"),

                new Perfume("Versace Jeans Couture Woman", "Female",
                        new String[]{"Plum", "Bergamot", "Peach"},
                        new String[]{"Rose", "Cyclamen", "Ylang-Ylang", "Jasmine"},
                        new String[]{"Cedar", "Amber", "Sandalwood", "Vanilla"},
                        "Moderate", "Moderate"),

                new Perfume("Versace Signature", "Female",
                        new String[]{"Guava", "Blackcurrant", "Lilac"},
                        new String[]{"Jasmine", "Lotus", "Orchid"},
                        new String[]{"Musk", "Cedarwood", "Cashmere Wood"},
                        "Moderate", "Moderate"),

        };
        
        // Add to list and BST
        for (Perfume p : perfumes) {
            list.add(p);
            bst.insert(p);
        }
    }
}