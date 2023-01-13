import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import styled from 'styled-components';

import LogIn from './components/templates/LogIn';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Showcase from './components/templates/Showcase';
import Sidebar from './components/organisms/Sidebar';
import User from './components/templates/User';
import UserEdit from './components/templates/UserEdit';
import SeriesList from './components/templates/SeriesList';
import PostPage from './components/templates/PostPage';
import PostCreatePage from './components/templates/PostCreatePage';
import Search from './components/templates/Search';
import Signup from './components/templates/Signup';

import GlobalStyled from './GlobalStyle';
import PublicRoute from './routes/PublicRoute';

const App = () => {
  return (
    <>
      <Reset />
      <GlobalStyled />
      <BrowserRouter>
        <Navigator />
        <Main>
          <Sidebar />
          <Routes>
            <Route path="/" element={<PublicRoute component={<Showcase />} />} />
            <Route path="/login" element={<PublicRoute component={<LogIn />} />} />
            <Route path="/signup" element={<PublicRoute component={<Signup />} />} />
            <Route path="/posts" element={<PublicRoute component={<PostList />} />} />
            <Route path="/posts/:id" element={<PublicRoute component={<PostPage />} />} />
            <Route path="/posts/new" element={<PublicRoute component={<PostCreatePage />} />} />
            <Route path="/series" element={<PublicRoute component={<SeriesList />} />} />
            <Route path="/user" element={<PublicRoute component={<User />} />} />
            <Route path="/user/edit" element={<PublicRoute component={<UserEdit />} />} />
            <Route path="/search/:keyword" element={<PublicRoute component={<Search />} />} />
          </Routes>
        </Main>
      </BrowserRouter>
    </>
  );
};

export default App;

const Main = styled.div`
  display: flex;
  justify-content: center;
  margin-top: var(--header-height);
`;
