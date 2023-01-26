import { createGlobalStyle } from 'styled-components';

const GlobalStyled = createGlobalStyle`
    :root {
      /* color */
      --gray-50: #EFEFEF;
      --gray-100: #DFDFDF;
      --gray-200: #CFCFCF;
      --gray-300: #BFBFBF;
      --gray-400: #AFAFAF;
      --gray-500: #757575;
      --gray-600: #545454;
      --gray-700: #333333;
      --gray-800: #1F1F1F;
      --gray-900: #141414;
      --purplegray-50: #E9E0F2;
      --purplegray-100: #DDD9E3;
      --purplegray-200: #D5CFDC;
      --purplegray-300: #C2BFC5;
      --purplegray-400: #97959A;
      --purplegray-500: #87838C;
      --purplegray-600: #6F6977;
      --purplegray-700: #50465B;
      --purplegray-800: #40354D;
      --purplegray-900: #30233F;
      --purple-50: #E0CCF5;
      --purple-100: #C099EB;
      --purple-200: #A166E2;
      --purple-300: #8133D8;
      --purple-400: #6200CE;
      --purple-500: #560DA7;
      --purple-600: #4F1490;
      --purple-700: #461F71;
      --purple-800: #412462;
      --purple-900: #3C2952;
      --blue-50: #CED8F8;
      --blue-100: #9EB1F1;
      --blue-200: #6D89EB;
      --blue-300: #3D62E4;
      --blue-400: #0C3BDD;
      --blue-500: #1238B6;
      --blue-600: #16369F;
      --blue-700: #1A3380;
      --blue-800: #1D3271;
      --blue-900: #1F3161;
      --green-50: #CDE7DA;
      --green-100: #9BCFB6;
      --green-200: #68B891;
      --green-300: #36A06D;
      --green-400: #048848;
      --green-500: #107343;
      --green-600: #176640;
      --green-700: #20553B;
      --green-800: #254D39;
      --green-900: #2A4437;
      --red-50: #F9D1CC;
      --red-100: #F3A399;
      --red-200: #ED7566;
      --red-300: #E74733;
      --red-400: #E11900;
      --red-500: #B21E10;
      --red-600: #95211A;
      --red-700: #702628;
      --red-800: #5D282E;
      --red-900: #4A2A35;
      --orange-50: #FDE4CC;
      --orange-100: #FBC899;
      --orange-200: #FAAD66;
      --orange-300: #F89133;
      --orange-400: #F67600;
      --orange-500: #C26410;
      --orange-600: #A2591A;
      --orange-700: #784B28;
      --orange-800: #63442E;
      --orange-900: #4E3C35;

      /* font-display */
      --display-l: 96px;
      --display-m: 52px;
      --display-s: 44px;
      --display-xs: 36px;

      /* font-heading */
      --heading-xxl: 40px;
      --heading-xl: 36px;
      --heading-l: 32px;
      --heading-m: 28px;
      --heading-s: 24px;
      --heading-xs: 20px;
    
      /* font-label */
      --label-l: 18px;
      --label-m: 16px;
      --label-s: 14px;
      --label-xs: 12px;
      
      /* font-paragraph */
      --paragraph-l: 18px;
      --paragraph-m: 16px;
      --paragraph-s: 14px;
      --paragraph-xs: 12px;  

      /* elevation */
      --elevation-00: ;
      --elevation-01: 0px 0px 1px rgba(40, 41, 61, 0.08), 0px 0.5px 2px rgba(96, 97, 112, 0.16);
      --elevation-02: 0px 0px 1px rgba(40, 41, 61, 0.04), 0px 2px 4px rgba(96, 97, 112, 0.16);
      --elevation-03: 0px 0px 2px rgba(40, 41, 61, 0.04), 0px 4px 8px rgba(96, 97, 112, 0.16);
      --elevation-04: 0px 2px 4px rgba(40, 41, 61, 0.04), 0px 8px 16px rgba(96, 97, 112, 0.16);
      --elevation-05: 0px 2px 8px rgba(40, 41, 61, 0.04), 0px 16px 24px rgba(96, 97, 112, 0.16);
      --elevation-06: 0px 2px 8px rgba(40, 41, 61, 0.08), 0px 20px 32px rgba(96, 97, 112, 0.24);

      /* boxShadow */
      --boxShadow-00: 3px 3px 0px;
      --boxShadow-01: 5px 5px 0px;
      --boxShadow-02: 10px 10px 0px;
      --boxShadow-07: 7px 7px 0px;
      --boxShadow-08: 8px 8px 0px;
      --boxShadow-stack: 2px 2px 0px #FFFFFF, 5px 5px 0px #000000, 7px 7px 0px #FFFFFF, 10px 10px 0px #000000;


      /* layout-size */
      --header-height: 100px;
      --body-width: 1440px;
      --sidebar-width: 159px;
      --content-width: 1056px;


      /* Blur */
      --blur-30: blur(30px);      
  }
  
  body { 
    font-family: 'Roboto', 'Noto Sans KR', sans-serif;
    font-style: normal;
  }
  `;

export default GlobalStyled;
