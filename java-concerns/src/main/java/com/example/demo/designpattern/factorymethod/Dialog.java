package com.example.demo.designpattern.factorymethod;

// The creator class declares the factory method that must
// return an object of a product class. The creator's subclasses
// usually provide the implementation of this method.
abstract class Dialog {
    // The creator may also provide some default implementation
    // of the factory method.
    abstract Button createButton();

    // Note that, despite its name, the creator's primary
    // responsibility isn't creating products. It usually
    // contains some core business logic that relies on product
    // objects returned by the factory method. Subclasses can
    // indirectly change that business logic by overriding the
    // factory method and returning a different type of product
    // from it.
    void render() {
        // Call the factory method to create a product object.
        Button okButton = createButton();
        // Now use the product.
        okButton.onClick(this::closeDialog);
        okButton.render();
    }

    abstract void closeDialog();
}

// Concrete creators override the factory method to change the
// resulting product's type.
class WindowsDialog extends Dialog {
    @Override
    Button createButton() {
        System.out.println("creating Window button");
        return new WindowsButton();
    }

    @Override
    void closeDialog() {
        // Close the dialog in Windows style.
    }
}

class WebDialog extends Dialog {
    @Override
    Button createButton() {
        System.out.println("creating HTML button");
        return new HTMLButton();
    }

    @Override
    void closeDialog() {
        // Close the dialog in Web style.
    }
}

// The product interface declares the operations that all
// concrete products must implement.
interface Button {
    void render();

    void onClick(Runnable f);
}

// Concrete products provide various implementations of the
// product interface.
class WindowsButton implements Button {
    @Override
    public void render() {
        // Render a button in Windows style.
        System.out.println("Rendering Window button");
    }

    @Override
    public void onClick(Runnable f) {
        // Bind a native OS click event.
        f.run();
    }
}

class HTMLButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering HTML button");
        // Return an HTML representation of a button.
    }

    @Override
    public void onClick(Runnable f) {
        // Bind a web browser click event.
        f.run();
    }
}

class Application {
    private Dialog dialog;

    // The application picks a creator's type depending on the
    // current configuration or environment settings.
    void initialize() {
        String config = readApplicationConfigFile();

        if (config.equals("Windows")) {
            dialog = new WindowsDialog();
        } else if (config.equals("Web")) {
            dialog = new WebDialog();
        } else {
            throw new RuntimeException("Error! Unknown operating system.");
        }
    }

    private String readApplicationConfigFile() {
        // Read the configuration file
        return "Windows"; // example
    }

    // The client code works with an instance of a concrete
    // creator, albeit through its base interface. As long as
    // the client keeps working with the creator via the base
    // interface, you can pass it any creator's subclass.
    void main() {
        initialize();
        dialog.render();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.main();
    }
}