let config = {
    title: 'HyLogger - ',
    home: 'docs/home.md',
    repo: 'HyDevelop/HyLogger',
    nav:
    [
        {
            title: 'Home', path: '/'
        },
        {
            title: 'Update Log', path: '/docs/update_log'
        },
        {
            title: 'Dropdown', type: 'dropdown', items:
            [
                {
                    title: 'A', path: '/A'
                },
                {
                    title: 'B', path: '/B'
                },
                {
                    title: 'C', path: 'https://github.com/'
                }
            ]
        }
    ],
    tocVisibleDepth: 3,
    plugins: []
};

docute.init(config);
