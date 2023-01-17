import { useState } from 'react';
import styled from 'styled-components';
import { MAIN, CATEGORIES } from '../../constants/Categories';
import Menu from '../molecules/sidbar/SidebarMenu';
import { SidebarMainButton } from '../atoms/Buttons';
import Selected from '../../styles/Selected';

const Container = styled.div`
  width: var(--sidebar-width);
  height: auto;
`;

const MenuList = styled.div`
  display: flex;
  position: fixed;
  z-index: 1;
  justify-content: flex-start;
  flex-direction: column;
`;

const SelectedButton = styled(SidebarMainButton)`
  ${Selected}
`;

const Sidebar = () => {
  const [selectedTab, setSelectedTab] = useState('Home');

  const onClick = e => setSelectedTab(e.target.textContent);

  return (
    <Container>
      <MenuList>
        {MAIN.map(mainArr => {
          const [path, name] = mainArr;
          return selectedTab === name ? (
            <SelectedButton key={name} to={path} onClick={onClick}>
              {name}
            </SelectedButton>
          ) : (
            <SidebarMainButton key={name} to={path} onClick={onClick}>
              {name}
            </SidebarMainButton>
          );
        })}
        {CATEGORIES.map(categoryArr => {
          const [category, ...tags] = categoryArr;
          return <Menu key={category} category={category} tags={tags} onClick={onClick} selectedTab={selectedTab} />;
        })}
      </MenuList>
    </Container>
  );
};

export default Sidebar;
