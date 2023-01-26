module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: ['plugin:react/recommended', 'airbnb', 'plugin:react/jsx-runtime', 'plugin:prettier/recommended'],
  overrides: [],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: ['react', 'prettier'],
  rules: {
    // js 파일에서 jsx 문법 사용시 발생하는 문제에서 .js 확장명 허용
    'react/jsx-filename-extension': [1, { extensions: ['.js', '.jsx'] }],
    // 개행문자 관련 에러 무시
    'linebreak-style': 0,
    // 'React' must be in scope when using JSX, jsx 사용시 React 스코프 내에서 사용되야 하는 에러 무시
    'prettier/prettier': ['error', { endOfLine: 'auto' }],
    // 함수형 컴포넌트의 정의 방식을 지정
    'react/function-component-definition': [2, { namedComponents: 'arrow-function' }],
    // prop-types validation check 비활성화
    'react/prop-types': 0,
    // props spreading 금지 비활성화
    'react/jsx-props-no-spreading': 'off',
    // import 오류
    'import/no-extraneous-dependencies': ['error', { devDependencies: true }],
  },
};
