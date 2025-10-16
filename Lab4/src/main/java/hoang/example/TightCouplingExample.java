package hoang.example;

import lombok.extern.slf4j.Slf4j;

interface IPrinter {
    void print(String message);
}

@Slf4j
class Printer implements IPrinter {
    public void print(String message) {
        log.info(message);
    }
}


class Report {
    private IPrinter printer;

    // Constructor injection
    public Report(IPrinter printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print("Generating report...");
    }
}
