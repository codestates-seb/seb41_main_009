import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import styled from 'styled-components';

import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Showcase from './components/templates/Showcase';
import Sidebar from './components/organisms/Sidebar';
import SeriesList from './components/templates/SeriesList';
import GlobalStyled from './GlobalStyle';
import PublicRoute from './routes/PublicRoute';

const App = () => {
  return (
    <>
      <Reset />
      <GlobalStyled />
      <Navigator />
      <Main>
        <Sidebar />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<PublicRoute component={<Showcase />} />} />
            <Route path="/signin" element={<PublicRoute component={<Signin />} />} />
            <Route path="/posts" element={<PublicRoute component={<PostList />} />} />
            <Route path="/series" element={<PublicRoute component={<SeriesList />} />} />
          </Routes>
        </BrowserRouter>
      </Main>
    </>
  );
};

export default App;

const Main = styled.div`
  display: flex;
  justify-content: center;
  margin-top: var(--header-height);
`;
