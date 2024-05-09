def call(String name = 'User') {
    properties([
        parameters([
            string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
        ])
    ])
    echo "Welcome, ${name}."
}