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
import PostPage from './components/templates/PostPage';
import PostCreatePage from './components/templates/PostCreatePage';
import Search from './components/templates/Search';
import SeriesPage from './components/templates/SeriesPage';
import Signup from './components/templates/Signup';
import SeriesListPage from './components/templates/SeriesListPage';
import ErrorPage from './components/templates/404ErrorPage';

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
            <Route path="/posts/:category" element={<PublicRoute component={<PostList />} />} />
            <Route path="/posts/:category/:id" element={<PublicRoute component={<PostPage />} />} />
            <Route path="/posts/new" element={<PublicRoute component={<PostCreatePage />} />} />
            <Route path="/series/:category" element={<PublicRoute component={<SeriesListPage />} />} />
            <Route path="/series/:category/:id" element={<PublicRoute component={<SeriesPage />} />} />
            <Route path="/user" element={<PublicRoute component={<User />} />} />
            <Route path="/user/edit" element={<PublicRoute component={<UserEdit />} />} />
            <Route path="/search/:keyword" element={<PublicRoute component={<Search />} />} />
            <Route path="*" element={<PublicRoute component={<ErrorPage />} />} />
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
